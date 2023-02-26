package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.Profil;
import tn.esprit.spring.services.IServiceProfil;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profil")

public class ProfilController {
    @Autowired
    private IServiceProfil serviceProfil;
    @PostMapping("/saveProfil")
    public void saveProfil(@RequestBody Profil profil){
        serviceProfil.addProfil(profil);
    }
    @PutMapping("/updateProfil")
    public void updateProfil(@RequestBody Profil profil){
        serviceProfil.updateProfil(profil);
    }
    @DeleteMapping("/deleteProfil/{id}")
    public void updateEquiment(@PathVariable int id){
        serviceProfil.deleteProfil(id);
    }
    @GetMapping("/findAll")
    public List<Profil> findAll(){
        return serviceProfil.displayProfil();
    }
    @PostMapping("/assignDiplomeToProfile/{idProfil}")
    public void assignDiplomeToProfile(@RequestParam("file") MultipartFile file, @PathVariable Integer idProfil) throws IOException {
        serviceProfil.assignDiplomeToProfile(idProfil,file);
    }
}



