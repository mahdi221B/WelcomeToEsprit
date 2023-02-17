package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;

import java.util.List;

public interface IServiceClassroom {
    public void addClassroom(Classroom classroom);
    public void updateClassroom(Classroom classroom);
    public void deleteClassroom(int id);
    public List<Classroom> displayClassroom();
    void addClassroomAndAssignToBlock(Classroom classroom, int idBlock);


}
