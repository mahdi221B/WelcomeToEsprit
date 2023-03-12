package tn.esprit.spring.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.AdmissionResult;
import tn.esprit.spring.entity.AdmissionType;

import javax.mail.MessagingException;

@Service
public interface IServiceAdmission {
    public String assignAdmissionToStudent(int idStudent, AdmissionType admissionType) throws MessagingException;
    public String makeAdmissionResult(int idAdmission, AdmissionResult admissionResult) throws MessagingException;
}
