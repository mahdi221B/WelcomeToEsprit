package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.JobOffer;

import java.util.List;


@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {


}
