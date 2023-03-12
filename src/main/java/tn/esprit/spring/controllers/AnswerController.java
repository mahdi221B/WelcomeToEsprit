package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Answer;
import tn.esprit.spring.entity.Interview;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.AnswerRepository;
import tn.esprit.spring.repositories.QuestionRepository;
import tn.esprit.spring.services.AnswerService;
import tn.esprit.spring.services.IAnswerService;
import tn.esprit.spring.services.IInterviewService;
import tn.esprit.spring.services.QuestionService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
@RestController
@AllArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final IAnswerService iAnswerService;
    private final QuestionRepository questionRepository;


    @PostMapping("/{questionId}")
    public ResponseEntity<Answer> submitAnswer(@PathVariable Integer questionId, @RequestBody Answer answer) {
        boolean isCorrect = iAnswerService.isAnswerCorrect(questionId, answer.getChosenOption());
        answer.setCorrect(isCorrect);

        if (isCorrect) {
            answer.incrementScore();
        }

        Question question = questionRepository.findById(questionId).orElse(null);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        answer.setQuestion(question);

        Answer savedAnswer = iAnswerService.saveAnswer(answer);
        return ResponseEntity.ok(savedAnswer);
    }

    // pourcentage correct answer

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateScore(@RequestBody Answer[] answers) {
        double percentage = iAnswerService.calculateScore(answers);
        return ResponseEntity.ok(percentage);

    }
}