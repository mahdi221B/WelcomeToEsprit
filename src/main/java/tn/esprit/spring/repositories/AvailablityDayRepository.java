package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.AvailablityDay;


@Repository
public interface AvailablityDayRepository extends JpaRepository<AvailablityDay, Integer> {
}
