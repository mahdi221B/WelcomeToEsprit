package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Profil;
import tn.esprit.spring.entity.User;

public interface UserRepo extends JpaRepository<User,Integer> {
}
