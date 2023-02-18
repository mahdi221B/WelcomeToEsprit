package tn.esprit.spring.services;

import tn.esprit.spring.entity.Interview;

import java.util.List;

public interface IInterviewService {
    public List<Interview> retrieveAllInterviews();
    public void deleteInterview(Integer id);
    public Interview retrieveInterviewById(Integer id);
    public Interview addInterview(Interview interview);
    public Interview updateInterview(Interview interview,Integer id);

}
