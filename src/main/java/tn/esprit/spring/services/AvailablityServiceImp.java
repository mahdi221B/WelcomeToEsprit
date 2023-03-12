package tn.esprit.spring.services;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.AvailablityDay;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.AvailablityDayRepository;
import tn.esprit.spring.repositories.AvailablityTimeRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AvailablityServiceImp implements  IAvailablity{
    private final AvailablityDayRepository availablityDayRepository;
    private final AvailablityTimeRepository availablityTimeRepository ;
    private final UserRepository userRepository ;


    @Override
    public AvailablityDay createAvailablityDay(AvailablityDay availablityDate, Integer id){
        User user = userRepository.findById(id).get();
        availablityDate.setUser(user);
        return availablityDayRepository.save(availablityDate);
    }
    @Override
    public List<AvailablityDay> getUserDisponbility(Integer id){
        User user = userRepository.findById(id).get();
        List<AvailablityDay> avialablityDays = user.getAvailablities();
        for (AvailablityDay  availablityDay : avialablityDays) {
            System.out.println(availablityDay.getIdDisbo());
            log.info("I'ma availabe starting from: ");
            log.info(availablityDay.getDate_debut_diso().toString());
            log.info("To: ");
            log.info(availablityDay.getDate_fin_diso().toString());
        }
        return avialablityDays;
    }
}
