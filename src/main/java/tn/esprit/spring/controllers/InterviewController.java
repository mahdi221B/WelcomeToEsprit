package tn.esprit.spring.controllers;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entity.CandidatType;
import tn.esprit.spring.repositories.InterviewRepository;
import tn.esprit.spring.services.IInterviewService;
import tn.esprit.spring.entity.Interview;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.services.InterviewServiceImp;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final IInterviewService iInterviewService;


    private InterviewRepository interviewRepository;

    @PostMapping("/add")
    @ResponseBody
    public Interview addInterview(@RequestBody Interview interview) {
        return iInterviewService.addInterview(interview);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Interview updateInterview(@RequestBody Interview interview, @PathVariable("id") Integer id) {
        return iInterviewService.updateInterview(interview, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteInterview(@PathVariable("id") Integer id) {
        iInterviewService.deleteInterview(id);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Interview getInterviewById(@PathVariable("id") Integer id) {
        return iInterviewService.retrieveInterviewById(id);
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<Interview> getAllInterview() {
        return iInterviewService.retrieveAllInterviews();
    }


    @GetMapping(value = "/afficherInterviewParCandidatEtscore/{candidatInterview}/{scoreInterview}")
    @ResponseBody
    public List<Interview> afficherInterviewParCandidatEtscore(@PathVariable("candidatInterview") CandidatType candidatInterview, @PathVariable("scoreInterview") int scoreInterview) {
        return iInterviewService.afficherInterviewParcandidatEtscore(candidatInterview, scoreInterview);
    }


    @PostMapping("/score")
    public ResponseEntity<Interview> calculateScore(@RequestBody Interview interview) {
        Interview updatedInterview = iInterviewService.calculateScore(interview);
        return ResponseEntity.ok(updatedInterview);
    }


    @GetMapping("/maxScore")
    public ResponseEntity<Interview> findInterviewWithMaxScore() {
        Interview interview = iInterviewService.findInterviewWithMaxScore();
        if (interview != null) {
            return ResponseEntity.ok(interview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/interviews")
    public ResponseEntity<?> add(@RequestBody Interview interview) {
        if (iInterviewService.containsBadWords(interview.getFeedback())) {
            return ResponseEntity.badRequest().body("Feedback contains bad words");
        } else {
            try {
                iInterviewService.addInterview(interview);
                return ResponseEntity.ok("Interview added successfully");
            } catch (InterviewServiceImp.BadWordsFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }


    @PutMapping("/{interviewId}/scores")
    public ResponseEntity<String> updateScore(@PathVariable Integer interviewId) {
        iInterviewService.calculateAndUpdateScore(interviewId);
        return ResponseEntity.ok("Score updated successfully");
    }


    @GetMapping("/best-interviewer")
    public ResponseEntity<String> getBestInterviewer() {
        List<Interview> interviews = iInterviewService.findAllIntervieweesOrderedByScoreDESC();
        StringBuilder messageBuilder = new StringBuilder("Interviewee scores in descending order:\n");
        for (Interview interview : interviews) {
            messageBuilder.append(interview.getInterviewee())
                    .append(" - ")
                    .append(interview.getScore())
                    .append("\n");
        }
        return ResponseEntity.ok(messageBuilder.toString());
    }


    @PostMapping("/interviews/sendEmailToIntervieweeWithMaxScore")
    public ResponseEntity<String> sendEmailToIntervieweeWithMaxScore() {
        try {
            iInterviewService.sendEmailToIntervieweeWithMaxScore();
            return ResponseEntity.ok("Email sent to interviewee with max score.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }
    }
}







