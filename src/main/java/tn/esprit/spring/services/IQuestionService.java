package tn.esprit.spring.services;

import tn.esprit.spring.entity.Mcqqestion;
import tn.esprit.spring.entity.Mcreponse;

import java.util.List;

public interface IQuestionService {
    public List<Mcqqestion> retrieveAllQuestions();
    public void deleteQuestion(Integer id);
    public Mcqqestion retrieveQuestionById(Integer id);
    public Mcqqestion addQuestion(Mcqqestion mcqqestion);
    public Mcqqestion updateQuestion(Mcqqestion mcqqestion,Integer id);
}
