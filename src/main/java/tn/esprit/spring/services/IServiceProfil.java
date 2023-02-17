package tn.esprit.spring.services;

import tn.esprit.spring.entity.Profil;

import java.util.List;

public interface IServiceProfil {
    public void addProfil(Profil profil);
    public void updateProfil(Profil profil);
    public void deleteProfil(int id);
    public List<Profil> displayProfil();
}
