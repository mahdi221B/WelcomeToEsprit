package tn.esprit.spring.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import tn.esprit.spring.entity.CsvQuestion;
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
    public FlatFileItemReader<CsvQuestion> questionItemReader() {
        FlatFileItemReader<CsvQuestion> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("C:\\Users\\ThinkPad\\Desktop\\questions.csv"));
        reader.setLineMapper(new DefaultLineMapper<CsvQuestion>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"enonce", "option1", "option2", "option3", "correctAnswer"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CsvQuestion>() {{
                setTargetType(CsvQuestion.class);
            }});
        }});
        return reader;
    }

    @Bean
    public QuestionItemProcessor questionItemProcessor() {
        return new QuestionItemProcessor();
    }

    @Bean
    public JobCompletionNotificationListener listener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    public JpaItemWriter<CsvQuestion> questionItemWriter() {
        JpaItemWriter<CsvQuestion> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Job importQuestionsJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importQuestionsJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JpaItemWriter<CsvQuestion> writer) {
        return stepBuilderFactory.get("step1")
                .<CsvQuestion, CsvQuestion>chunk(10)
                .reader(questionItemReader())
                .processor((Function<? super CsvQuestion, ? extends CsvQuestion>) questionItemProcessor())
                .writer(writer)
                .build();
    }

}

