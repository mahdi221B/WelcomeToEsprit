package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
