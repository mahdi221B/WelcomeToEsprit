package tn.esprit.spring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.AnswerRepository;
import tn.esprit.spring.repositories.QuestionRepository;


@Service
public class AnswerService implements IAnswerService{



    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;


    public Answer updateAnswer(Answer answer) {
        return answerRepository.save(answer);
    }






    public boolean isAnswerCorrect(Integer questionId, String chosenOption) {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question == null) {
            return false;
        }
        return question.getCorrectAnswer().equals(chosenOption);

    }

    public Answer saveAnswer(Answer answer) {
        boolean isCorrect = isAnswerCorrect(answer.getQuestion().getIdQuestion(), answer.getChosenOption());
        answer.setCorrect(isCorrect);
        if (isCorrect) {
            answer.setScore(answer.getScore() );
        }
        return answerRepository.save(answer);
    }


    public double calculateScore(Answer[] answers) {
        int numCorrect = 0;
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                numCorrect++;
            }
        }
        double percentage = (double) numCorrect / answers.length * 100;
        return percentage;
    }
}
