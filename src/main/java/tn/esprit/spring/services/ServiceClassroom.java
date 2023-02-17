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
    public void addClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public void updateClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public void deleteClassroom(int id) {
        classroomRepository.deleteById(id);
    }

    @Override
    public List<Classroom> displayClassroom() {
        return classroomRepository.findAll();
    }

    @Override
    public void addClassroomAndAssignToBlock(Classroom classroom, int idBlock) {
        Block block = blockRepository.findById(idBlock).orElse(null);
        classroom.setBlock(block);
        classroomRepository.save(classroom);
    }
}
