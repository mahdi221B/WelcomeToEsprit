package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.entity.Profil;

public interface ProfilRepository extends JpaRepository<Profil,Integer> {
}
