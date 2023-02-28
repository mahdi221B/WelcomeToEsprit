package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.AppointmentBooking;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.AppointmentBookingRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppointmentBookingImp implements IAppointmentBooking {

    private final UserRepository userRepository;

   // @Autowired
    private final JavaMailSender javaMailSender;

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

    public void sendConfirmationEmail(AppointmentBooking appointment) {
        List<User> adminUsers = userRepository.findAdminUsersByAvailability(appointment.getDate_reservation());
        if (!adminUsers.isEmpty()) {
            for (User adminUser : adminUsers) {
                // envoyer un email de confirmation
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("bacem.mallek999@gmail.com");
                message.setTo(adminUser.getEmail());
                message.setSubject("Confirmation de rendez-vous");
                message.setText("Votre rendez-vous a été confirmé pour le " + appointment.getDate_reservation());
                javaMailSender.send(message);
            }
        }
        }
    }

