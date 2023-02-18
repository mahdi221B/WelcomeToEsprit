package tn.esprit.spring.controllers;


import tn.esprit.spring.entity.Reclamation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.services.IReclamationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reclamation")
public class ReclamationController {
    private final IReclamationService iReclamationService;
    @PostMapping("/add")
    @ResponseBody
    public Reclamation addReclamation(@RequestBody Reclamation reclamation){
        return iReclamationService.addReclamation(reclamation);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Reclamation updateReclamation(@RequestBody Reclamation reclamation, @PathVariable("id") Integer id){
        return iReclamationService.updateReclamation(reclamation,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteReclamation(@PathVariable("id") Integer id){
        iReclamationService.deleteReclamation(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Reclamation getReclamationById(@PathVariable("id") Integer id){
        return iReclamationService.retrieveReclamationById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Reclamation> getAllReclamation(){
        return iReclamationService.retrieveAllReclamations();
    }
}
