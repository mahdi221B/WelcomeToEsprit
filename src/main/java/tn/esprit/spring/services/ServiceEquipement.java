package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.repositories.ClassroomRepository;
import tn.esprit.spring.repositories.EquipementRepository;

import java.util.List;
@Service
public class ServiceEquipement implements IServiceEquipement{
    @Autowired
    private EquipementRepository equipementRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Override
    public void addEquipement(Equipement equipement) {equipementRepository.save(equipement);}

    @Override
    public void updateEquipement(Equipement equipement) { equipementRepository.save(equipement);}

    @Override
    public void deleteEquipement(int id) {equipementRepository.deleteById(id);}

    @Override
    public List<Equipement> displayEquipement() {return equipementRepository.findAll();}

    @Override
    public void addEquipementAndAssignToClassroom(Equipement equipement, int idClassroom) {
        Classroom classroom= classroomRepository.findById(idClassroom).orElse(null);
      equipement.setClassroom(classroom);
      equipementRepository.save(equipement);

    }
}
