package tn.esprit.spring.services;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.repositories.ApplicationFormRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@AllArgsConstructor
@Service
public class ApplicationFormImp implements IApplicationForm {


    private ApplicationFormRepository applicationFormRepository;

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



}
