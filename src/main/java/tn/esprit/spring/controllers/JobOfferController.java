package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.JobOffer;

import tn.esprit.spring.services.IJobOffer;

import java.util.List;

@RestController
@RequestMapping("/offres")

public class JobOfferController {
    @Autowired
    private IJobOffer service;

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



}
