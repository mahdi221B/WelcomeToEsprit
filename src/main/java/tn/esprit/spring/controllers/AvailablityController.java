package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Appointment;
import tn.esprit.spring.entity.AvailablityDay;
import tn.esprit.spring.entity.AvailablityTime;
import tn.esprit.spring.services.IAvailablity;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/availablity")
public class AvailablityController {
    private final IAvailablity availablity ;

    @PostMapping("/addDay/{id}")
    public AvailablityDay createAvailablityDay(@RequestBody AvailablityDay availablityDay, @PathVariable("id") Long id) {
        return availablity.createAvailablityDay(availablityDay,id);
    }
    @PostMapping("/addTime/{id}")
    public AvailablityTime createAvailablityTime(@RequestBody AvailablityTime availablityTime,@PathVariable("id") Integer id) {
        return availablity.createAvailablityTime(availablityTime,id);
    }
    @GetMapping("/getUserDisponbility/{id}")
    @ResponseBody
    public List<AvailablityDay> getUserDisponbility(@PathVariable("id") Long id) {
        return availablity.getUserDisponbility(id);
    }
}
