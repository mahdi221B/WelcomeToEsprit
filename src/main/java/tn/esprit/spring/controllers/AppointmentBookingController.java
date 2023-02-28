package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.AppointmentBooking;


import tn.esprit.spring.services.AppointmentBookingImp;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class AppointmentBookingController {

    @Autowired
    private AppointmentBookingImp reservationService;

    @PostMapping
    public AppointmentBooking create(@RequestBody AppointmentBooking reservation) {
        return reservationService.create(reservation);
    }


    @GetMapping("/{id}")
    public AppointmentBooking getById(@PathVariable Long id) {
        return reservationService.getById(id);
    }

    @GetMapping
    public List<AppointmentBooking> getAll() {
        return reservationService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }






    @PutMapping("/{id}")
    public ResponseEntity<AppointmentBooking> updateAppointmentBooking(@RequestBody AppointmentBooking appointmentBooking, @PathVariable Long id) {
        AppointmentBooking updatedAppointmentBooking = reservationService.updateAppointmentBooking(appointmentBooking, id);

        if (updatedAppointmentBooking != null) {
            return new ResponseEntity<>(updatedAppointmentBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/send")
    public void sendmail(@RequestBody AppointmentBooking reservation) {
        reservationService.sendConfirmationEmail(reservation);
    }


   /*
    @PutMapping
    public AppointmentBooking update(@RequestBody AppointmentBooking reservation) {
        return reservationService.update(reservation);
    }*/

}