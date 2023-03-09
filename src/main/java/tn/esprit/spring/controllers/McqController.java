package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.AnswerRepository;
import tn.esprit.spring.repositories.QuestionRepository;
import tn.esprit.spring.services.IMcqService;
import tn.esprit.spring.services.IQuestionService;
import tn.esprit.spring.services.McqService;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/mcq")
public class McqController {
    private final IMcqService iMcqService;


    @PostMapping("/generate/{diplomaTitle}")
    public Mcq generateMcq(@PathVariable String diplomaTitle) throws IOException {
        return iMcqService.generateMcq(diplomaTitle);
    }




}