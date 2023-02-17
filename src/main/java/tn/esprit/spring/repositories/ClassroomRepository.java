package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
}
