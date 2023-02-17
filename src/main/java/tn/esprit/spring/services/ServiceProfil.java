package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Profil;
import tn.esprit.spring.repositories.ProfilRepository;

import java.util.List;

@Service
public class ServiceProfil implements IServiceProfil{
    @Autowired
    private ProfilRepository profilRepository;
    @Override
    public void addProfil(Profil profil) {profilRepository.save(profil);}

    @Override
    public void updateProfil(Profil profil) { profilRepository.save(profil);}

    @Override
    public void deleteProfil(int id) {profilRepository.deleteById(id);}

    @Override
    public List<Profil> displayProfil() {return profilRepository.findAll();}
}