package tn.esprit.spring.services;


import ch.qos.logback.core.subst.Tokenizer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.McqRepository;
import tn.esprit.spring.repositories.QuestionRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j

@AllArgsConstructor
@Service
public class QuestionService implements IQuestionService

{

final QuestionRepository questionRepository;
    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }











}


