package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.AppEvent;
@Repository

public interface AppEventRepository extends JpaRepository<AppEvent, Long> {
}
