package tn.esprit.spring.controllers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Appointment;

import tn.esprit.spring.services.AppointmentImp;

import java.util.List;


@RestController
@RequestMapping("/rendez-vous")
@Api
public class AppointmentController {

    @Autowired
    private AppointmentImp rendezVousService;

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return rendezVousService.getById(id);
    }

    @GetMapping("/all")
    public List<Appointment> getAll() {
        return rendezVousService.getAll();
    }

    @PostMapping("/create")
    public Appointment create(@RequestBody Appointment rendezVous) {
        return rendezVousService.create(rendezVous);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        rendezVousService.deleteById(id);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment Appointment, @PathVariable Long id) {
        Appointment updateAppointment = rendezVousService.updateAppointment(Appointment, id);

        if (updateAppointment != null) {
            return new ResponseEntity<>(updateAppointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



/*
    @PutMapping("/{id}")
    public Appointment update(@RequestBody Appointment rendezVous, @PathVariable Long id) {
        rendezVous.setId(id);
        return rendezVousService.update(rendezVous);
    }*/

    }
}
