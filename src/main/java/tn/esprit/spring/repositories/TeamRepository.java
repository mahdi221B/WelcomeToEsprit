package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.entity.Team;

@Repository

public interface TeamRepository extends JpaRepository<Team, Long> {
}
