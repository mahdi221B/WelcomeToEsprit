package tn.esprit.spring.repositories;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.AppointmentBooking;

@Repository
public interface AppointmentBookingRepository extends JpaRepository<AppointmentBooking, Long> {
}
