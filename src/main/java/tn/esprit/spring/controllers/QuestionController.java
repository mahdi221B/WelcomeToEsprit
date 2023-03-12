package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.services.IQuestionService;
import tn.esprit.spring.services.IReclamationService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final IQuestionService iQuestionService;





}
