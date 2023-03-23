package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.services.IServiceEquipement;

import java.util.List;

@RestController
@RequestMapping("/equipement")
@Api
public class EquipementController {
    @Autowired
    private IServiceEquipement serviceEquipement;
    @PostMapping("/saveEquipement")
    public void saveEquipement(@RequestBody Equipement equipement){
        serviceEquipement.addEquipement(equipement);
    }
    @PutMapping("/updateEquipement")
    public void updateEquipement(@RequestBody Equipement equipement){
        serviceEquipement.updateEquipement(equipement);
    }
    @DeleteMapping("/deleteEquipement/{id}")
    public void updateEquiment(@PathVariable int id){
        serviceEquipement.deleteEquipement(id);
    }
    @GetMapping("/findAll")
    public List<Equipement> findAll(){
        return serviceEquipement.displayEquipement();
    }
@PostMapping("/addEquipementAndAssignToClassroom/{id}")
    public void addEquipementAndAssignToClassroom(@RequestBody Equipement equipement , @PathVariable int id ){
        serviceEquipement.addEquipementAndAssignToClassroom(equipement, id);
    }
}



