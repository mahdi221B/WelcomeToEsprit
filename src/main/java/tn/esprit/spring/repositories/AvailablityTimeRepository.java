package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.AppointmentBooking;
import tn.esprit.spring.entity.AvailablityTime;

@Repository

public interface AvailablityTimeRepository extends JpaRepository<AvailablityTime, Integer> {
}
