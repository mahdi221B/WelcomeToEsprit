package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.StatistiqueDTO;
import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.services.ApplicationFormImp;
import tn.esprit.spring.services.IApplicationForm;
import tn.esprit.spring.services.IJobOffer;
import tn.esprit.spring.services.StatistiqueDTOImp;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistiques")
public class StatistiqueDTOController {


    @Autowired
    private IJobOffer jobOfferService;
    private IApplicationForm applicationFormService;
    private ApplicationFormRepository applicationFormRepository;
    private ApplicationFormImp applicationFormImp;




//--------------------------------------------------------------------------------------------------------

    private final StatistiqueDTOImp statistiqueService;

    public StatistiqueDTOController(StatistiqueDTOImp statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping("")
    public List<StatistiqueDTO> getStatistiques() {
        return statistiqueService.getStatistiques();
    }


}
