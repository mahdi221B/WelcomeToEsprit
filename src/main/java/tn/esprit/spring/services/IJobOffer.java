package tn.esprit.spring.services;



import tn.esprit.spring.entity.JobOffer;

import java.util.List;

public interface IJobOffer {
    JobOffer createJobOffer(JobOffer JobOffer);
    JobOffer getJobOfferById(Long id);
    List<JobOffer> getAllJobOffers();
    JobOffer updateJobOffer(JobOffer JobOffer , Long id);
    void deleteJobOffer(Long id);


}
