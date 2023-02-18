package tn.esprit.spring.services;

import tn.esprit.spring.entity.Appointment;


public interface IAppointment {

    Appointment updateAppointment(Appointment Appointment, Long id);
}
