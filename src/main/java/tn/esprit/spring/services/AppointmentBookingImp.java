package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.AppointmentBooking;
import tn.esprit.spring.repositories.AppointmentBookingRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppointmentBookingImp implements IAppointmentBooking {




    private AppointmentBookingRepository reservationRepository;

    public AppointmentBooking create(AppointmentBooking reservation) {
        return reservationRepository.save(reservation);
    }

    public AppointmentBooking getById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<AppointmentBooking> getAll() {
        return reservationRepository.findAll();
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }




    public AppointmentBooking updateAppointmentBooking(AppointmentBooking appointmentBooking, Long id) {
        Optional<AppointmentBooking> optionalAppointmentBooking = reservationRepository.findById(id);

        if (optionalAppointmentBooking.isPresent()) {
            AppointmentBooking existingAppointmentBooking = optionalAppointmentBooking.get();
           // existingAppointmentBooking.setUser(AppointmentBooking.getUser());
            existingAppointmentBooking.setRdv(appointmentBooking.getRdv());

            existingAppointmentBooking.setDate_reservation(appointmentBooking.getDate_reservation());



            return reservationRepository.save(existingAppointmentBooking);
        } else {
            return null;
        }
    }





/*
    public AppointmentBooking update(AppointmentBooking reservation) {
        return reservationRepository.save(reservation);
    }
*/
}
