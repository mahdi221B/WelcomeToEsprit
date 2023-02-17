package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.Profil;
import tn.esprit.spring.services.IServiceProfil;

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
}



