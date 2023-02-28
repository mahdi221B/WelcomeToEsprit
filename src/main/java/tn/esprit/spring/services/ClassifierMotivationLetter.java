package tn.esprit.spring.services;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.ApplicationForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
@Component
public class ClassifierMotivationLetter {


    private final List<String> keyWords = Arrays.asList("motivation", "Java", "motivation3");

    public List<ApplicationForm> classify(List<ApplicationForm> candidats) {
        List<ApplicationForm> classifiedCandidats = new ArrayList<>();
        for (ApplicationForm candidat : candidats) {
            if (containsKeyWord(candidat.getMotivationLetter())) {
                classifiedCandidats.add(candidat);
            }
        }
        return classifiedCandidats;
    }

    private boolean containsKeyWord(String motivationLetter) {
        for (String keyWord : keyWords) {
            if (motivationLetter.contains(keyWord)) {
                return true;
            }
        }
        return false;
    }


}
