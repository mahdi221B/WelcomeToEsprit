package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.entity.JobOffer;
import tn.esprit.spring.entity.StatistiqueDTO;
import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.repositories.JobOfferRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class StatistiqueDTOImp implements IStatistiqueDTO {

   // @Autowired
    private JobOfferRepository jobOfferRepository;

   // @Autowired
    private ApplicationFormRepository applicationFormRepository;




    public List<StatistiqueDTO> getStatistiques() {
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        List<StatistiqueDTO> statistiques = new ArrayList<>();

        for (JobOffer jobOffer : jobOffers) {
            StatistiqueDTO statistique = new StatistiqueDTO();
            statistique.setSpecialite(jobOffer.getSpecialty());
            statistique.setNbOffres(1L);
            statistique.setNbCandidats((long) jobOffer.getFormulairesCandidature().size());
            statistiques.add(statistique);
        }

        Map<String, StatistiqueDTO> map = new HashMap<>();

        for (StatistiqueDTO statistique : statistiques) {
            if (map.containsKey(statistique.getSpecialite())) {
                StatistiqueDTO existingStatistique = map.get(statistique.getSpecialite());
                existingStatistique.setNbOffres(existingStatistique.getNbOffres() + statistique.getNbOffres());
                existingStatistique.setNbCandidats(existingStatistique.getNbCandidats() + statistique.getNbCandidats());
            } else {
                map.put(statistique.getSpecialite(), statistique);
            }
        }

        return new ArrayList<>(map.values());

    }





}
