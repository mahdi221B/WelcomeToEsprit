package tn.esprit.spring.repositories;

import tn.esprit.spring.entity.Mcreponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface McreponseRepository extends JpaRepository<Mcreponse, Integer> {
}
