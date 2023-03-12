package tn.esprit.spring.services;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.InterviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Service
@AllArgsConstructor
public class InterviewServiceImp implements IInterviewService {


    private final InterviewRepository interviewRepository;

private final EmailService emailService;

    public class BadWordsFoundException extends RuntimeException {
        public BadWordsFoundException(String message) {
            super(message);
        }
    }

    @Override
    public List<Interview> retrieveAllInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public void deleteInterview(Integer id) {
        interviewRepository.delete(interviewRepository.findById(id).get());
    }

    @Override
    public Interview retrieveInterviewById(Integer id) {
        return interviewRepository.findById(id).get();
    }

    @Override
    public Interview addInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public Interview updateInterview(Interview interview, Integer id) {
        interview.setId(id);
        return interviewRepository.save(interview);
    }


    @Override
    public List<Interview> afficherInterviewParcandidatEtscore(CandidatType candidatType, int score) {
        return interviewRepository.findAllByCandidatTypeAndScoreGreaterThan(candidatType, score);
    }


    @Override
    public Interview calculateScore(Interview interview) {

        int communicationScore = interview.getCommunicationScore();
        int technicalScore = interview.getTechnicalScore();
        int professionalismScore = interview.getProfessionalismScore();
        int score = (communicationScore + technicalScore + professionalismScore);
        interview.setScore(score);


        if (score > 10) {
            String intervieweeName = interview.getInterviewee();
            System.out.println(intervieweeName + " scored " + score + "you passed the interview");
        }

        return interviewRepository.save(interview);
    }

    @Override
    public Interview findInterviewWithMaxScore() {
        List<Interview> interviewsSortedByScoreDesc = interviewRepository.findAllSortedByScoreDesc();
        if (!interviewsSortedByScoreDesc.isEmpty()) {
            return interviewsSortedByScoreDesc.get(0);
        }
        return null;
    }


    private static final List<String> BAD_WORDS = Arrays.asList("bad", "words");

    public boolean containsBadWords(String input) {
        String regex = "\\b(" + String.join("|", BAD_WORDS) + ")\\b";
        Pattern badWordsPattern = Pattern.compile(regex); // comparaison
        Matcher inputMatcher = badWordsPattern.matcher(input);
        return inputMatcher.find();
    }


    public void calculateAndUpdateScore(Integer interviewId) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new EntityNotFoundException("Interview not found with id: " + interviewId));
        int totalScore = interview.getCommunicationScore() + interview.getTechnicalScore() + interview.getProfessionalismScore();
        interview.setScore(totalScore);
        interview.setFeedback("Score updated to " + totalScore);
        interviewRepository.save(interview);
    }


    public List<Interview> findAllIntervieweesOrderedByScoreDESC() {
        return interviewRepository.findAllByOrderByScoreDesc();

    }



    public void sendEmailToIntervieweeWithMaxScore() throws MessagingException {
        Interview interview = findInterviewWithMaxScore();
        if (interview != null) {
            String email = interview.getEmailAddress();
            String subject = "Congratulations on your top interview score!";
            String body = "Dear Interviewee,\n\n" +
                    "Congratulations on achieving the highest interview score. " +
                    "We are impressed by your skills and professionalism.\n\n" +
                    "Best regards,\n" +
                    "The Interview Team";
            emailService.sendEmail(email, subject, body);
        }
    }





    public Interview getInterviewById(Integer id) {
        return interviewRepository.findById(id).orElse(null);
    }



    public List<String> getNotSelectedInterviewees() {
        List<String> allInterviewees = interviewRepository.getAllInterviewees();
        List<String> selectedInterviewees = interviewRepository.getSelectedInterviewees();
        allInterviewees.removeAll(selectedInterviewees);
        return allInterviewees;
    }
    public List<Interview> getAllInterviewees() {
        return interviewRepository.findAll();
    }

}



