package tn.esprit.spring.services;

import tn.esprit.spring.entity.Reclamation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.repositories.ReclamationRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReclamationServiceImp implements IReclamationService{


    private final ReclamationRepository reclamationRepository;

    @Override
    public List<Reclamation> retrieveAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public void deleteReclamation(Integer id) {
        reclamationRepository.delete(reclamationRepository.findById(id).get());
    }

    @Override
    public Reclamation retrieveReclamationById(Integer id) {
        return reclamationRepository.findById(id).get();
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation, Integer id) {
        reclamation.setId(id);
        return reclamationRepository.save(reclamation);
    }
}
