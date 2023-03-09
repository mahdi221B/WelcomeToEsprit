package tn.esprit.spring.services;

import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;

import java.util.Optional;

public interface IQuestionService {

    public Optional<Question> getQuestionById(Integer id);


}
