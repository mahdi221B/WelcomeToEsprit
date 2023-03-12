package tn.esprit.spring.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.ApplicationForm;

import java.util.List;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long> {

    /////------------ trier les candidatures en fonction de la pertinence de la lettre de motivation -------////////
    @Query("SELECT af FROM ApplicationForm af WHERE af.motivationLetter LIKE %:keyword% ORDER BY af.motivationRelevance DESC")
    List<ApplicationForm> findByMotivationLetterContainingOrderByMotivationRelevanceDesc(@Param("keyword") String keyword);

     List<ApplicationForm> findAll();



}
