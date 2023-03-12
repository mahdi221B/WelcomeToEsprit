package tn.esprit.spring.services;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.AppointmentBookingRepository;
import tn.esprit.spring.repositories.UserRepository;


import java.util.*;

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


    public void sendConfirmationEmail(AppointmentBooking appointment , Long id ) {

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
                            new PhoneNumber(bestTeacher.getNum_tel()), // numéro de téléphone du destinataire
                            new PhoneNumber("+15673343207"), // numéro de téléphone Twilio
                            "Votre rendez-vous a été confirmé pour le : " + appointment.getDate_reservation())
                    .create();

        }
        User user = userRepository.findById(id).get();
        appointment.setUser(user);
        reservationRepository.save(appointment);
    }


    //-------------------------------------------------------------------------------------------------------//

    public List<String> getSortedAppointmentsWithTeachers() {
        List<AppointmentBooking> appointments = reservationRepository.findAll();
        List<String> result = new ArrayList<>();

        for (AppointmentBooking appointment : appointments) {
            Date day = appointment.getDate_reservation();
            List<User> availableTeachers = new ArrayList<>();
            User bestTeacher = null;
            long maxMargin = 0;

            for (User u : userRepository.findTeachersdisponibilty()) {
                for (AvailablityDay availablityDay : u.getAvailablities()) {
                    if (availablityDay.getDate_fin_diso().after(day) && availablityDay.getDate_debut_diso().before(day)) {
                        long margin = availablityDay.getDate_fin_diso().getTime() - availablityDay.getDate_debut_diso().getTime();
                        if (margin > maxMargin) {
                            bestTeacher = u;
                            maxMargin = margin;
                        }
                    }
                }
            }

            if (bestTeacher != null) {
                String appointmentInfo = "Candidat : " + appointment.getUser().getNom() + ", Teacher : " + bestTeacher.getNom() + ", Date de rendez-vous : " + appointment.getDate_reservation();
                result.add(appointmentInfo);
            }
        }

        Collections.sort(result);
        return result;
    }



}
