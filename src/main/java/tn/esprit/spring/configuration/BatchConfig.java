package tn.esprit.spring.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import tn.esprit.spring.entity.CsvQuestion;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.QuestionDto;
import tn.esprit.spring.services.JobCompletionNotificationListener;
import tn.esprit.spring.services.QuestionItemProcessor;


import javax.persistence.EntityManagerFactory;
import java.util.function.Function;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public FlatFileItemReader<QuestionDto> reader() {
        FlatFileItemReader<QuestionDto> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("C:\\Users\\ThinkPad\\Desktop\\questions.csv"));
        reader.setLineMapper(new DefaultLineMapper<QuestionDto>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("enonce", "option1", "option2", "option3", "correctAnswer");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<QuestionDto>() {{
                setTargetType(QuestionDto.class);
            }});
        }});
        return reader;
    }

    @Bean
    public JpaItemWriter<Question> writer() {
        JpaItemWriter<Question> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step step1(JpaItemWriter<Question> writer) {
        return stepBuilderFactory.get("step1")
                .<QuestionDto, Question>chunk(10)
                .reader(reader())
                .processor(new ItemProcessor<QuestionDto, Question>() {
                    @Override
                    public Question process(QuestionDto questionDto) throws Exception {
                        Question question = new Question();
                        question.setEnonce(questionDto.getEnonce());
                        question.setOption1(questionDto.getOption1());
                        question.setOption2(questionDto.getOption2());
                        question.setOption3(questionDto.getOption3());
                        question.setCorrectAnswer(questionDto.getCorrectAnswer());
                        return question;
                    }
                })
                .writer(writer)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
}










