package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.repositories.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppointmentImp implements IAppointment {


    private AppointmentRepository rendezVousRepository;

    public Appointment create(Appointment rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    public Appointment getById(Long id) {
        return rendezVousRepository.findById(id).orElse(null);
    }

    public List<Appointment> getAll() {
        return rendezVousRepository.findAll();
    }

    public void deleteById(Long id) {
        rendezVousRepository.deleteById(id);
    }

/*
    public Appointment update(Appointment rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }*/


    public Appointment updateAppointment(Appointment appointment, Long id) {
        Optional<Appointment> optionalAppointment = rendezVousRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();
            existingAppointment.setTitre(appointment.getTitre());
            existingAppointment.setDescription(appointment.getDescription());
            existingAppointment.setDateRnv(appointment.getDateRnv());
            existingAppointment.setReservations(appointment.getReservations());

            return rendezVousRepository.save(existingAppointment);
        } else {
            return null;
        }
    }
}