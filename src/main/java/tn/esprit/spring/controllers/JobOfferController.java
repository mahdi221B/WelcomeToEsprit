package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.entity.JobOffer;

import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.repositories.JobOfferRepository;
import tn.esprit.spring.services.ApplicationFormImp;
import tn.esprit.spring.services.IJobOffer;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/offres")

public class JobOfferController {

    private IJobOffer service;

    /////////////////////////////////////pour ajouter et affecter ////////////////////////////////////////////

    private ApplicationFormImp applicationFormService;

    private ApplicationFormRepository applicationFormRepository;


    private JobOfferRepository jobOfferRepository;

    ///////////////////////////////////////////////////////////////////////







    @PostMapping
    public JobOffer createJobOffer(@RequestBody JobOffer JobOffer) {
        return service.createJobOffer(JobOffer);
    }

    @GetMapping("/{id}")
    public JobOffer getJobOfferById(@PathVariable Long id) {
        return service.getJobOfferById(id);
    }

    @GetMapping
    public List<JobOffer> getAllJobOffers() {
        return service.getAllJobOffers();
    }

   /* @PutMapping
    public JobOffer updateJobOffer(@RequestBody JobOffer JobOffer) {
        return service.updateJobOffer(JobOffer);
    }*/

        @PutMapping("/{id}")
        public ResponseEntity<JobOffer> updateJobOffer(@RequestBody JobOffer JobOffer, @PathVariable Long id) {
            JobOffer updatedJobOffer = service.updateJobOffer(JobOffer, id);

            if (updatedJobOffer != null) {
                return new ResponseEntity<>(updatedJobOffer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


    @DeleteMapping("/{id}")
    public void deleteJobOffer(@PathVariable Long id) {
        service.deleteJobOffer(id);
    }


///////////////---------------------------------------------------------------------///////////////

    @PostMapping("/job-offers/{jobOfferId}/application-forms")
    public ResponseEntity<ApplicationForm> createApplicationFormAndAssignToJobOffer(@RequestBody ApplicationForm applicationForm, @PathVariable Long jobOfferId) {
        try {
            JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                    .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with id " + jobOfferId));

            applicationForm.setJobOffers(Collections.singletonList(jobOffer));

            ApplicationForm savedApplicationForm = applicationFormRepository.save(applicationForm);

            jobOffer.getApplicationForms().add(savedApplicationForm);
            jobOfferRepository.save(jobOffer);

            return ResponseEntity.ok().body(savedApplicationForm);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }





}
