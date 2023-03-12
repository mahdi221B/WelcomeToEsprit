package tn.esprit.spring.services;

import tn.esprit.spring.entity.Reclamation;

import java.util.List;

public interface IReclamationService {

    public List<Reclamation> retrieveAllReclamations();
    public void deleteReclamation(Integer id);
    public Reclamation retrieveReclamationById(Integer id);
    public Reclamation addReclamation(Reclamation reclamation);
    public Reclamation updateReclamation(Reclamation comment,Integer id);


    public void NumberOfReclamationsPerStatus();
}
