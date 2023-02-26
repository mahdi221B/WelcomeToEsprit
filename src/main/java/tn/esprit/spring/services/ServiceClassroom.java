package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.repositories.BlockRepository;
import tn.esprit.spring.repositories.ClassroomRepository;

import java.util.List;

@Service
public class ServiceClassroom implements IServiceClassroom {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private BlockRepository blockRepository;
    @Override
    public String addClassroom(Classroom classroom) {
        String erreur="";
        if(classroom.getCapacity()==0){
            erreur+="-Capacite null\n";
        }
        if(classroom.getRoomnumber()==0){
            erreur+="-Room Number null\n";
        }
        if(classroom.getFloornumber()==0){
            erreur+="-Floor Number null\n";
        }
        if(erreur.length()>0){
            return erreur;
        }
        else{
            classroomRepository.save(classroom);
            return "Ajout avec success";
        }

    }

    @Override
    public void updateClassroom(Classroom classroom) { classroomRepository.save(classroom);}


    @Override
    public void deleteClassroom(int id) {
        classroomRepository.deleteById(id);
    }

    @Override
    public List<Classroom> displayClassroom() {
        return classroomRepository.findAll();
    }

    @Override
    public String addClassroomAndAssignToBlock(Classroom classroom, int idBlock) {
        Block block = blockRepository.findById(idBlock).orElse(null);
        classroom.setBlock(block);
        String erreur="";
        if(classroom.getCapacity()==0){
            erreur+="-Capacite null\n";
        }
        if(classroom.getRoomnumber()==0){
            erreur+="-Room Number null\n";
        }
        if(classroom.getFloornumber()==0){
            erreur+="-Floor Number null\n";
        }
        if(erreur.length()>0){
            return erreur;
        }
        else{
            classroomRepository.save(classroom);
            return "Ajout avec success";
        }
    }


    }

