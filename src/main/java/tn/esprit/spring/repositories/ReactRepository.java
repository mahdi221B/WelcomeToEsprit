package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.React;
@Repository

public interface ReactRepository extends JpaRepository<React, Integer> {
}