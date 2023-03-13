package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.AdmissionRepository;
import tn.esprit.spring.repositories.ClassroomRepository;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepo;
import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class ServiceAdmission implements IServiceAdmission{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private SmsSender smsSender;
    public String assignAdmissionToStudent(int idStudent, AdmissionType admissionType) throws MessagingException {
        User student=userRepo.findById(idStudent).orElse(null);
        Admission a=new Admission();
        a.setStudent(student);
        Role roleTeacher=roleRepository.findAll()
                .stream()
                .filter(r->r.getRoleName().equals("TEACHER"))
                .findFirst()
                .orElse(null);
        if(roleTeacher!=null){
            User teacher=roleTeacher.getUsers()
                    .stream()
                    .filter(t->t.getAvailability().equals(Availability.AVAILABLE))
                    .findFirst().orElse(null);
            if(teacher!=null){
                a.setTeacher(teacher);
                Classroom classroom=classroomRepository.findAll()
                        .stream().filter(c->c.isStatus()).findFirst().orElse(null);
                if(classroom!=null){
                    a.setClassroom(classroom);
                    a.setDate(LocalDateTime.now().plusHours(24));
                    a.setAdmissionType(admissionType);
                    a.setAdmissionResult(AdmissionResult.PENDING);
                    admissionRepository.save(a);
                    teacher.setAvailability(Availability.NOTAVAILABLE);
                    classroom.setStatus(false);
                    userRepo.save(teacher);
                    classroomRepository.save(classroom);
                    //email sms
                    emailSender.send(student.getEmailAddress(),"Admission to Esprit", "Admission successfuly for student:"+student.getFirstName()+"At: "+LocalDateTime.now().plusHours(24)+" With :"+teacher.getFirstName());
                    //smsSender.sendSms("+21625979571","Admission successfuly for student:"+student.getFirstName()+"At: "+LocalDateTime.now().plusHours(24)+" With :"+teacher.getFirstName());
                    return "Admission successfuly for student:"+student.getFirstName()+"At: "+LocalDateTime.now().plusHours(24)+" With :"+teacher.getFirstName();
                }
                else{
                    return("no available classroom ");
                }
            }
            else{
                return("no available teacher ");
            }
        }
        else{
            return("no teacher exist!");
        }
    }
    public String makeAdmissionResult(int idAdmission,AdmissionResult admissionResult) throws MessagingException {
        Admission admission=admissionRepository.findById(idAdmission).orElse(null);
        admission.setAdmissionResult(admissionResult);
        admission.getTeacher().setAvailability(Availability.AVAILABLE);
        admission.getClassroom().setStatus(true);
        admissionRepository.save(admission);
        if(admissionResult.equals(AdmissionResult.ACCEPTED)){
            //send email&sms
            emailSender.send(admission.getStudent().getEmailAddress(),"Admission to Esprit", "congrats you have been accepted!");

            return "congrats you have been accepted!";
        }
        else{
            //send email&sms
            emailSender.send(admission.getStudent().getEmailAddress(),"Admission to Esprit", "unfortunately you haven't been accepted");

            return "unfortunately you haven't been accepted";
        }
    }
    @Scheduled(cron = "0/30 * * * * *")
    public void admissionStatistics(){
        int numberOfAdmissions=admissionRepository.findAll().size();
        long numberOfAdmssionsAccepted=admissionRepository.findAll().stream().filter(a->a.getAdmissionResult().equals(AdmissionResult.ACCEPTED)).count();
        long numberOfAdmssionsRejected=admissionRepository.findAll().stream().filter(a->a.getAdmissionResult().equals(AdmissionResult.REJECTED)).count();
        System.out.println( "total number of admission : "+numberOfAdmissions+"\ntotal of accepted admission :"+numberOfAdmssionsAccepted+"\ntotal of rejected admission :"+numberOfAdmssionsRejected);

    }
}
