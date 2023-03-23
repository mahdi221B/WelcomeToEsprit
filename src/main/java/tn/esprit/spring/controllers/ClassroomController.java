package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.services.IServiceClassroom;

import java.util.List;



    @RestController
    @RequestMapping("/classroom")
    @Api
    public class ClassroomController {
        @Autowired
        private IServiceClassroom serviceClassroom;
        @PostMapping("/saveClassroom")
        public void saveClassroom(@RequestBody Classroom classroom){
            serviceClassroom.addClassroom(classroom);
        }
        @PutMapping("/updateClassroom")
        public void updateClassroom(@RequestBody Classroom classroom){
            serviceClassroom.updateClassroom(classroom);
        }
        @DeleteMapping("/deleteClassroom/{id}")
        public void updateClassroom(@PathVariable int id){
            serviceClassroom.deleteClassroom(id);
        }
        @GetMapping("/findAll")
        public List<Classroom> findAll(){
            return serviceClassroom.displayClassroom();
        }
        @PostMapping("/addClassroomAndAssignToBlock/{id}")
        public void addClassroomAndAssignToBlock(@RequestBody Classroom classroom ,@PathVariable int id ){
            serviceClassroom.addClassroomAndAssignToBlock(classroom, id);
        }
}
