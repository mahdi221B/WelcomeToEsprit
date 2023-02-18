package tn.esprit.spring.services;

import tn.esprit.spring.entity.Mcreponse;
import tn.esprit.spring.entity.Reclamation;

import java.util.List;

public interface IReponseService {
    public List<Mcreponse> retrieveAllReponses();
    public void deleteReponse(Integer id);
    public Mcreponse retrieveReponseById(Integer id);
    public Mcreponse addReponse(Mcreponse mcreponse);
    public Mcreponse updateReponse(Mcreponse mcreponse,Integer id);

}
