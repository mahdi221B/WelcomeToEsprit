package tn.esprit.spring.repositories;

import tn.esprit.spring.entity.Mcqqestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface McqqestionRepository extends JpaRepository<Mcqqestion, Integer> {
}
