package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.services.IServiceClassroom;

import java.util.List;



    @RestController
    @RequestMapping("/classroom")
    public class ClassroomController {
        @Autowired
        private IServiceClassroom serviceClassroom;
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
}
