package tn.esprit.spring.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tn.esprit.spring.entity.Reclamation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.services.IReclamationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reclamation")
public class ReclamationController {
    private final IReclamationService iReclamationService;
    @PostMapping("/add")
    public ResponseEntity<Reclamation> addReclamation(@Valid @RequestBody Reclamation reclamation, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if(bindingResult.hasErrors()){
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        Reclamation createdReclamation = iReclamationService.addReclamation(reclamation);
        return ResponseEntity.ok(createdReclamation);
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

