package tn.esprit.spring.services;

import com.google.zxing.WriterException;
import tn.esprit.spring.entity.CandidatType;
import tn.esprit.spring.entity.Format;
import tn.esprit.spring.entity.Interview;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IInterviewService {
    public List<Interview> retrieveAllInterviews();
    public void deleteInterview(Integer id);
    public Interview retrieveInterviewById(Integer id);
    public Interview addInterview(Interview interview);
    public Interview updateInterview(Interview interview,Integer id);

    public List<Interview> afficherInterviewParcandidatEtscore(CandidatType candidatType, int score);

    public Interview calculateScore(Interview interview);

    public Interview findInterviewWithMaxScore();


    public boolean containsBadWords(String input);

    public void calculateAndUpdateScore(Integer interviewId);

    public List<Interview> findAllIntervieweesOrderedByScoreDESC();

    public void sendEmailToIntervieweeWithMaxScore() throws MessagingException;





    public Interview getInterviewById(Integer id);




    public List<String> getNotSelectedInterviewees();

    public List<Interview> getAllInterviewees();




}
