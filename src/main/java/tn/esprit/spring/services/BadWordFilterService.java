package tn.esprit.spring.services;

import java.util.Arrays;
import java.util.List;

public class BadWordFilterService {
    private List<String> badWords = Arrays.asList("bad", "offensive", "inappropriate");

    public boolean containsBadWord(String text) {
        for (String badWord : badWords) {
            if (text.toLowerCase().contains(badWord)) {
                return true;
            }
        }
        return false;
    }
}
