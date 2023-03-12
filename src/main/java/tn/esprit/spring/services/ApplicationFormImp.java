package tn.esprit.spring.services;



import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.entity.JobOffer;
import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.repositories.JobOfferRepository;

import javax.persistence.EntityNotFoundException;

import java.util.*;

@AllArgsConstructor
@Service
public class ApplicationFormImp implements IApplicationForm {

    private ApplicationFormRepository applicationFormRepository;
   // @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private FileSystemRepository fileSystemRepository ;


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


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////---- logique pour calculer la pertinence de la lettre de motivation---------////
    public int calculateMotivationRelevance(ApplicationForm af, String keyword) {
        String motivationLetter = af.getMotivationLetter();
        if (motivationLetter == null) {
            return 0;
        }
        int relevance = 0;
        for (String word : motivationLetter.split("\\s+")) {
            if (word.equalsIgnoreCase(keyword)) {
                relevance++;
            }
        }
        return relevance;
    }

    //----récupérer les candidatures triées par pertinence de la lettre de motivation------/////////////

    public List<ApplicationForm> findApplicationsByMotivationLetter(String keyword) {
        List<ApplicationForm> applications = applicationFormRepository.findAll();
        for (ApplicationForm af : applications) {
            int relevance = calculateMotivationRelevance(af, keyword);
            af.setMotivationRelevance(relevance);
        }
        return applicationFormRepository.findByMotivationLetterContainingOrderByMotivationRelevanceDesc(keyword);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    public List<ApplicationForm> classifyCandidates() {
        List<ApplicationForm> candidates = applicationFormRepository.findAll();
        List<ApplicationForm> classifiedCandidates = new ArrayList<>();

        // mots-clés positifs
        List<String> positiveKeywords = Arrays.asList("motivation", "créativité","Proactivité","java","softskills");

        for (ApplicationForm candidate : candidates) {
            int experience = candidate.getExperience();
            String noteMotivation = candidate.getMotivationLetter().toLowerCase();

            // vérifie s'il contient des mots-clés positifs
            boolean containsPositiveKeyword = positiveKeywords.stream().anyMatch(noteMotivation::contains);

            if (experience >= 5 && containsPositiveKeyword) {
                classifiedCandidates.add(candidate);
            } else if (experience >= 3 && containsPositiveKeyword) {
                classifiedCandidates.add(candidate);
            }
        }
        sendEmails(classifiedCandidates);
        return classifiedCandidates;
    }
    private void sendEmails(List<ApplicationForm> classifiedCandidates) {
        String subject = "Congratulations! You have been classified!";
        String text = "Dear %s,\n\nCongratulations! Your application has been classified as a strong candidate.\n\nbook an appointment:\n\nhttp://localhost:8088/springMVC/reservations/send\n\nBest regards,\nThe HR team";

        for (ApplicationForm candidate : classifiedCandidates) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(candidate.getEmail());
            message.setSubject(subject);
            message.setText(String.format(text, candidate.getName()));

            javaMailSender.send(message);
        }
    }


}
