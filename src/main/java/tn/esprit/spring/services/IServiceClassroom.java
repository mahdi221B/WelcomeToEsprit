package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;

import java.util.List;

public interface IServiceClassroom {
    public String addClassroom(Classroom classroom);

    public void updateClassroom(Classroom classroom);

    public void deleteClassroom(int id);
    public List<Classroom> displayClassroom();
    String addClassroomAndAssignToBlock(Classroom classroom, int idBlock);



}
