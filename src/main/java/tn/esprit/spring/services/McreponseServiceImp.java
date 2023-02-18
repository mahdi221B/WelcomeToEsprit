package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Mcreponse;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.repositories.McreponseRepository;
import tn.esprit.spring.repositories.ReclamationRepository;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class McreponseServiceImp implements IReponseService{


    private final McreponseRepository mcreponseRepository;

    @Override
    public List<Mcreponse> retrieveAllReponses() {
        return mcreponseRepository.findAll();
    }

    @Override
    public void deleteReponse(Integer id) {
        mcreponseRepository.delete(mcreponseRepository.findById(id).get());
    }

    @Override
    public Mcreponse retrieveReponseById(Integer id) {
        return mcreponseRepository.findById(id).get();
    }

    @Override
    public Mcreponse addReponse(Mcreponse mcreponse) {
        return mcreponseRepository.save(mcreponse);
    }

    @Override
    public Mcreponse updateReponse(Mcreponse mcreponse, Integer id) {
        mcreponse.setId(id);
        return mcreponseRepository.save(mcreponse);
    }
}