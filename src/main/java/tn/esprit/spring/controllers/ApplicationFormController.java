package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.services.IApplicationForm;

import java.util.List;

@RestController
@RequestMapping("/formulaires")
public class    ApplicationFormController {

    @Autowired
    private IApplicationForm applicationFormService;

    @PostMapping
    public ResponseEntity<ApplicationForm> createApplicationForm(@RequestBody ApplicationForm applicationForm) {
        ApplicationForm createdApplicationForm = applicationFormService.createApplicationForm(applicationForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApplicationForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationForm> getApplicationFormById(@PathVariable("id") Long id) {
        ApplicationForm applicationForm = applicationFormService.getApplicationFormById(id);
        return ResponseEntity.ok(applicationForm);
    }


    @GetMapping
    public List<ApplicationForm> getAllJobOffers() {
        return applicationFormService.getAllApplicationForms();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicationFormById(@PathVariable("id") Long id) {
        applicationFormService.deleteApplicationFormById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationForm> updateApplicationForm(@PathVariable("id") Long id, @RequestBody ApplicationForm applicationForm) {
        ApplicationForm updatedApplicationForm = applicationFormService.updateApplicationForm(applicationForm, id);
        return ResponseEntity.ok(updatedApplicationForm);
    }
}
