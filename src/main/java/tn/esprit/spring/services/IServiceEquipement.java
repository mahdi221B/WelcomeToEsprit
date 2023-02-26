package tn.esprit.spring.services;

import tn.esprit.spring.entity.Classroom;
import tn.esprit.spring.entity.Equipement;

import java.util.List;

public interface IServiceEquipement {
    public void addEquipement(Equipement equipement);
    public void updateEquipement(Equipement equipement);
    public void deleteEquipement(int id);
    public List<Equipement> displayEquipement();
    String addEquipementAndAssignToClassroom(Equipement equipement ,int idClassroom);
}
