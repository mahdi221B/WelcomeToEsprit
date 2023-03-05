package tn.esprit.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.services.ApplicationFormImp;

import tn.esprit.spring.services.ClassifierMotivationLetter;
import tn.esprit.spring.services.IApplicationForm;
import tn.esprit.spring.services.TextClassificationImp;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/formulaires")
public class ApplicationFormController {
    private IApplicationForm applicationFormService;
    @Autowired
    private ApplicationFormImp appservice ;

    //@Autowired
   private ApplicationFormRepository applicationFormRepository;
   private ClassifierMotivationLetter classifierMotivationLetter;
   private final TextClassificationImp textClassificationService;
    @GetMapping("/clasify")
    public ResponseEntity<ApplicationForm> classify(@RequestBody ApplicationForm applicationForm) {
       // textClassificationService.classifyCandidates(applicationForm);
        return ResponseEntity.ok(applicationForm);
    }






    /*  @PostMapping("/{jobOfferId}")
    public ApplicationForm createApplicationForm(@RequestBody ApplicationForm applicationForm, @PathVariable Long jobOfferId) {
        return applicationFormService.createApplicationForm(applicationForm, jobOfferId);
    }*/
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


/////////////////////////////////////////////////////////////////////////////////////////
    //----keyword------------
    @GetMapping("/applications")
    public List<ApplicationForm> getApplications(@RequestParam("keyword") String keyword) {
        List<ApplicationForm> applications = appservice.findApplicationsByMotivationLetter(keyword);
        return applications;
    }
/////////////////-makthar-//////////////////////////////////////////////////////////////////////////////




   /* public ApplicationFormController(ApplicationFormImp applicationFormService) {
        this.applicationFormService = applicationFormService;
    }*/


/////--////////////////////////////-/////////////////////////////////////////////////////
  /*  @GetMapping("/classified")
    public List<ApplicationForm> getClassifiedApplicationForms() {

        try {
            List<ApplicationForm> applicationForms = appservice.getAllApplicationForms();
            //System.out.println(applicationForms);
            return appservice.classifyMotivationLetters(applicationForms);

        } catch (Exception e) {
            // Gérer l'exception ici
            return null;
        }
    }*/
    ///////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/candidats")
  /*  public List<ApplicationForm> getCandidats() {
        // Récupérer la liste des candidats depuis la base de données
        List<ApplicationForm> candidats = applicationFormRepository.findAll();

        // Classifier les candidats
        return classifierMotivationLetter.classify(candidats);
    }*/
    public List<ApplicationForm> getCandidats(@RequestParam("keyword") String keyword) {
        return applicationFormRepository.findByMotivationLetterContainingOrderByMotivationRelevanceDesc(keyword);
    }
//------------------------------------------------------------------------------------------------------//

    @GetMapping("/classifiedd")
    public ResponseEntity<List<ApplicationForm>> getClassifiedCandidates() {
        List<ApplicationForm> classifiedCandidates = appservice.classifyCandidates();
        return new ResponseEntity<>(classifiedCandidates, HttpStatus.OK);

    }

}
