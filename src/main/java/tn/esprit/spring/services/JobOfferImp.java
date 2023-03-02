package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.JobOffer;
import tn.esprit.spring.repositories.JobOfferRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;





@AllArgsConstructor
@Service
public class JobOfferImp implements IJobOffer {




       // @Autowired
        private JobOfferRepository repository;

        public JobOffer createJobOffer(JobOffer jobOffer) {

        //    redisTemplate.opsForValue().set("JobOffer:" + jobOffer.getIdOffre(), jobOffer);
          //  return repository.save(jobOffer);//
            return repository.save(jobOffer);
        }

        public JobOffer getJobOfferById(Long id) {
            return repository.findById(id).orElse(null);
        }
       // @Transactional
        public List<JobOffer> getAllJobOffers() {
            return repository.findAll();


        }

    /*public JobOffer updateJobOffer(JobOffer JobOffer) {
        return repository.save(JobOffer);
    }*/

    public JobOffer updateJobOffer(JobOffer JobOffer, Long id) {
        Optional<JobOffer> optionalJobOffer = repository.findById(id);

        if (optionalJobOffer.isPresent()) {
            JobOffer existingJobOffer = optionalJobOffer.get();
            existingJobOffer.setTitle(JobOffer.getTitle());
            existingJobOffer.setSpecialty(JobOffer.getSpecialty());

            existingJobOffer.setDescription(JobOffer.getDescription());
            existingJobOffer.setRequirements(JobOffer.getRequirements());
            existingJobOffer.setLocation(JobOffer.getLocation());


            return repository.save(existingJobOffer);
        } else {
            return null;
        }
    }


        public void deleteJobOffer(Long id) {
            repository.deleteById(id);
        }

}
