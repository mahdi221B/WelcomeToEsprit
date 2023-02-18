package tn.esprit.spring.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.ApplicationForm;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long> {

}
