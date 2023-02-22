package tn.esprit.spring.services;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.entity.JobOffer;
import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.repositories.JobOfferRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@AllArgsConstructor
@Service
public class ApplicationFormImp implements IApplicationForm {


    private ApplicationFormRepository applicationFormRepository;

   // @Autowired
    private JobOfferRepository jobOfferRepository;

   /* public ApplicationForm createApplicationForm(ApplicationForm applicationForm, Long jobOfferId) {
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).orElseThrow(() -> new EntityNotFoundException("Job offer not found with id " + jobOfferId));
        applicationForm.setJobOffer(jobOffer);
        return applicationFormRepository.save(applicationForm);
    }*/

    public ApplicationForm createApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(applicationForm);
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ApplicationForm not found with id " + id));
    }


    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }



    public void deleteApplicationFormById(Long id) {
        applicationFormRepository.deleteById(id);
    }

    public ApplicationForm updateApplicationForm(ApplicationForm applicationForm, Long id) {
        ApplicationForm existingApplicationForm = applicationFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ApplicationForm not found with id " + id));

        existingApplicationForm.setName(applicationForm.getName());
        existingApplicationForm.setEmail(applicationForm.getEmail());
        existingApplicationForm.setMotivationLetter(applicationForm.getMotivationLetter());

        return applicationFormRepository.save(existingApplicationForm);
    }


    /////////----------------------------------////////////////////////////////////////
    public ApplicationForm saveApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(applicationForm);
    }

    public JobOffer saveJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public void assignApplicationFormToJobOffer(Long applicationFormId, Long jobOfferId) {
        ApplicationForm applicationForm = applicationFormRepository.findById(applicationFormId)
                .orElseThrow(() -> new EntityNotFoundException("ApplicationForm not found with id " + applicationFormId));

        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with id " + jobOfferId));

        applicationForm.getJobOffers().add(jobOffer);
        jobOffer.getApplicationForms().add(applicationForm);

        applicationFormRepository.save(applicationForm);
        jobOfferRepository.save(jobOffer);
    }

    public void createApplicationFormAndAssignToJobOffer(ApplicationForm applicationForm, Long jobOfferId) {
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with id " + jobOfferId));

        applicationForm.getJobOffers().add(jobOffer);
        jobOffer.getApplicationForms().add(applicationForm);

        applicationFormRepository.save(applicationForm);
        jobOfferRepository.save(jobOffer);
    }


}
