package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Mcqqestion;
import tn.esprit.spring.entity.Mcreponse;
import tn.esprit.spring.repositories.McqqestionRepository;
import tn.esprit.spring.repositories.McreponseRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class McqqestionServiceImp implements IQuestionService{


    private final McqqestionRepository mcqqestionRepository;

    @Override
    public List<Mcqqestion> retrieveAllQuestions() {
        return mcqqestionRepository.findAll();
    }

    @Override
    public void deleteQuestion(Integer id) {
        mcqqestionRepository.delete(mcqqestionRepository.findById(id).get());
    }

    @Override
    public Mcqqestion retrieveQuestionById(Integer id) {
        return mcqqestionRepository.findById(id).get();
    }

    @Override
    public Mcqqestion addQuestion(Mcqqestion mcqqestion) {
        return mcqqestionRepository.save(mcqqestion);
    }

    @Override
    public Mcqqestion updateQuestion(Mcqqestion mcqqestion, Integer id) {
        mcqqestion.setId(id);
        return mcqqestionRepository.save(mcqqestion);
    }
}