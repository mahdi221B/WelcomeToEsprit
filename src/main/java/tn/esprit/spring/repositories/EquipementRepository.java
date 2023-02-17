package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Equipement;

public interface EquipementRepository extends JpaRepository<Equipement,Integer> {
}
