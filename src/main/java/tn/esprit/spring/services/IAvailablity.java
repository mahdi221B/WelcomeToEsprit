package tn.esprit.spring.services;

import tn.esprit.spring.entity.AvailablityDay;
import tn.esprit.spring.entity.AvailablityTime;

import java.util.List;

public interface IAvailablity {
   // AvailablityTime createAvailablityTime(AvailablityTime availablityTime,Integer id);
    AvailablityDay createAvailablityDay(AvailablityDay availablityDate, Integer id);
    public List<AvailablityDay> getUserDisponbility(Integer id);
    }
