package tn.esprit.spring.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Profil;

import java.util.List;

public interface IServiceProfil {
    public void addProfil(Profil profil);
    public void updateProfil(Profil profil);
    public void deleteProfil(int id);
    public List<Profil> displayProfil();
    public void assignDiplomeToProfile(Integer idProfil, MultipartFile multipartFile);


}
