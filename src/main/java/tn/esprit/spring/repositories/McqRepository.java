package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Reclamation;

@Repository
public interface McqRepository extends JpaRepository<Mcq, Integer> {
}
