package tn.esprit.spring.services;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.AppointmentBookingRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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


    public void sendConfirmationEmail(AppointmentBooking appointment  ) {

        Date Day = appointment.getDate_reservation();
      //  User candidate = candidate.getApplicationForm();
        //Date hour = appointment.getHeure_reservation();
        List<User> availableTeachers = new ArrayList<>();
        User bestTeacher = null;
        long maxMargin = 0;
        for (User u : userRepository.findTeachersdisponibilty()) {
            for (AvailablityDay availablityDay : u.getAvailablities()) {
                if (availablityDay.getDate_fin_diso().after(Day) && availablityDay.getDate_debut_diso().before(Day)) {
                    long margin = availablityDay.getDate_fin_diso().getTime() - availablityDay.getDate_debut_diso().getTime();
                    if (margin > maxMargin) {
                        bestTeacher = u;
                        maxMargin = margin;
                    }
                }
            }
        }


        // availableTeachers.add();
        if (bestTeacher != null) {
            // envoyer un email de confirmation
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bacem.mallek999@gmail.com");
            message.setTo(bestTeacher.getEmail());

            message.setSubject("Confirmation de rendez-vous");
            message.setText("Dear " +bestTeacher.getNom()+ "\n\nVotre rendez-vous a été confirmé pour le : \n\n" + appointment.getDate_reservation());

            javaMailSender.send(message);



            // envoyer un SMS de confirmation
            Twilio.init("ACa05f2dc3a10a59a8b08c10df99c9ef45", "16072b9b5ba3dff0a7ccf3fe6a407f16");
            Message.creator(
                            new PhoneNumber("+21627501097"), // numéro de téléphone du destinataire
                            new PhoneNumber("+15673343207"), // numéro de téléphone Twilio
                            "Votre rendez-vous a été confirmé pour le : " + appointment.getDate_reservation())
                    .create();


        }

      /*  public void sendSMS(String phoneNumber, String message) {
            // Use an SMS API to send the message to the phone number
            // Here's an example of how to send an SMS using the Twilio API
            Twilio.init("AC3061431a78b36297bf3893b414d3947e", "e1fcc3243ae9f568724bf5fc50f64502");
            com.twilio.rest.api.v2010.account.Message.creator(
                    new com.twilio.type.PhoneNumber(phoneNumber),
                    new com.twilio.type.PhoneNumber("+15674122294"),
                    message).create();
        }// hedhi f service wel 7ajet eli bin "" mta3 l compte mte3i zid mta3ek*/

    }






}
