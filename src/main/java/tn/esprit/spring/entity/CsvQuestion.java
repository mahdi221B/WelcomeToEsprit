package tn.esprit.spring.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvQuestion {

    private String enonce;
    private String option1;
    private String option2;
    private String option3;
    private String correctAnswer;

}
