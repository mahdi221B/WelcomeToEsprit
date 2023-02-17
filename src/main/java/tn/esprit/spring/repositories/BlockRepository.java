package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;

public interface BlockRepository extends JpaRepository<Block,Integer> {
}
