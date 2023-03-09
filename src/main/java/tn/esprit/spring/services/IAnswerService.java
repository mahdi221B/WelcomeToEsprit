package tn.esprit.spring.services;

import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.entity.Question;

import java.util.Optional;

public interface IAnswerService {



    public boolean isAnswerCorrect(Integer idQuestion, String chosenOption);
    public Answer updateAnswer(Answer answer);
    public Answer saveAnswer(Answer answer);

    public double calculateScore(Answer[] answers);


}
