package tn.esprit.spring.services;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Profil;
import tn.esprit.spring.repositories.ProfilRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ServiceProfil implements IServiceProfil{
    @Autowired
    private ProfilRepository profilRepository;
    @Autowired
    private IServiceFile iServiceFile;
    @Override
    public void addProfil(Profil profil) {profilRepository.save(profil);}

    @Override
    public void updateProfil(Profil profil) { profilRepository.save(profil);}

    @Override
    public void deleteProfil(int id) {profilRepository.deleteById(id);}

    @Override
    public List<Profil> displayProfil() {return profilRepository.findAll();}
    public void assignDiplomeToProfile(Integer idProfil, MultipartFile multipartFile){
        Profil p=profilRepository.findById(idProfil).orElse(null);
        try {
            String filename=iServiceFile.uploadFile(multipartFile);
            p.setDiplomas(filename);

            String diplomaReader=iServiceFile.getFileOCR(filename);
            System.out.println(diplomaReader);
            if(diplomaReader.toLowerCase().contains("info")){
                p.setEducation("Informatique");
            }
            if(diplomaReader.toLowerCase().contains("electro")){
                p.setEducation("Electronique");
            }
            if(diplomaReader.toLowerCase().contains("mecani")){
                p.setEducation("Mecanique");
            }
            if(diplomaReader.toLowerCase().contains("busines")){
                p.setEducation("Business");
            }
            profilRepository.save(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }
    public void assignWorkExperienceToProfile(Integer idProfil, MultipartFile multipartFile){
        Profil p=profilRepository.findById(idProfil).orElse(null);
        String filename= null;
        try {
            filename = iServiceFile.uploadFile(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        p.setWorkExperience(filename);
        profilRepository.save(p);
    }
    public void assignCertificationToProfile(Integer idProfil, MultipartFile multipartFile){
        Profil p=profilRepository.findById(idProfil).orElse(null);
        String filename= null;
        try {
            filename = iServiceFile.uploadFile(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        p.setCertification(filename);
        profilRepository.save(p);
    }

}