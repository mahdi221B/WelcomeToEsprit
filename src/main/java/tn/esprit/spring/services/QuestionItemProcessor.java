package tn.esprit.spring.services;

import org.springframework.batch.item.ItemProcessor;
import tn.esprit.spring.entity.CsvQuestion;
import tn.esprit.spring.entity.Question;

public class QuestionItemProcessor  implements ItemProcessor<CsvQuestion, Question> {

    @Override
    public Question process(CsvQuestion csvQuestion) throws Exception {
        // Transform the Question data into a Question entity
        Question question = new Question();
        question.setEnonce(csvQuestion.getEnonce());
        question.setOption1(csvQuestion.getOption1());
        question.setOption2(csvQuestion.getOption2());
        question.setOption3(csvQuestion.getOption3());
        question.setCorrectAnswer(csvQuestion.getCorrectAnswer());

        return question;
    }

}
