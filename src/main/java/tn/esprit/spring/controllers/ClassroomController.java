package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.AdmissionResult;
import tn.esprit.spring.entity.AdmissionType;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.services.IServiceAdmission;
import tn.esprit.spring.services.IServiceClassroom;

import javax.mail.MessagingException;
import java.util.List;



    @RestController
    @RequestMapping("/classroom")
    public class ClassroomController {
        @Autowired
        private IServiceClassroom serviceClassroom;
        @Autowired
        private IServiceAdmission serviceAdmission;
        @PostMapping("/saveClassroom")
        public void saveClassroom(@RequestBody Classroom classroom){
            serviceClassroom.addClassroom(classroom);
        }
        @PutMapping("/updateClassroom")

        public void updateBlock(@RequestBody Classroom classroom){serviceClassroom.addClassroom(classroom);

        }
        @DeleteMapping("/deleteClassroom/{id}")
        public void delete(@PathVariable int id){
            serviceClassroom.deleteClassroom(id);
        }
        @GetMapping("/findAll")
        public List<Classroom> findAll(){
            return serviceClassroom.displayClassroom();
        }
        @PostMapping("/addClassroomAndAssignToBlock/{id}")
        public String addClassroomAndAssignToBlock(@RequestBody Classroom classroom ,@PathVariable int id ){
            return serviceClassroom.addClassroomAndAssignToBlock(classroom, id);
        }
        @PostMapping("/makeAdmission/{idStudent}/{admissionType}")
        public String makeAdmission(@PathVariable int idStudent, @PathVariable AdmissionType admissionType) throws MessagingException {
            return serviceAdmission.assignAdmissionToStudent(idStudent,admissionType);
        }
        @PutMapping("/makeAdmissionResult/{idAdmission}/{admissionResult}")
        public String makeAdmissionResult(@PathVariable int idAdmission, @PathVariable AdmissionResult admissionResult) throws MessagingException {
            return serviceAdmission.makeAdmissionResult(idAdmission, admissionResult);
        }

}
