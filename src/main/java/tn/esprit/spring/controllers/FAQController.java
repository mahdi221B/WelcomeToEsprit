package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.FAQ;
import tn.esprit.spring.services.IServiceFAQ;

import java.util.List;
@RestController
@RequestMapping("/faq")
public class FAQController {

    @Autowired
    private IServiceFAQ serviceFAQ;
    @PostMapping("/saveFAQ")
    public void saveFAQ(@RequestBody FAQ faq){
        serviceFAQ.addFAQ(faq);
    }
    @PutMapping("/updateFAQ")
    public void updateFAQ(@RequestBody FAQ faq){
        serviceFAQ.updateFAQ(faq);
    }
    @DeleteMapping("/deleteFAQ/{id}")
    public void updateEquiment(@PathVariable int id){
        serviceFAQ.deleteFAQ(id);
    }
    @GetMapping("/findAll")
    public List<FAQ> findAll(){
        return serviceFAQ.displayFAQ();
    }
}
