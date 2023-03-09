package tn.esprit.spring.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Getter
@Setter
public class QcmDto {

    private String question;
    private List<String> options;
    private int correctAnswer;

}
