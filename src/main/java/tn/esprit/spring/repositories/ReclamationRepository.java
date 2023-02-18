package tn.esprit.spring.repositories;

import tn.esprit.spring.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
}
