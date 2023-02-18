package tn.esprit.spring.services;

import tn.esprit.spring.repositories.InterviewRepository;
import tn.esprit.spring.entity.Interview;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InterviewServiceImp implements IInterviewService {


    private final InterviewRepository interviewRepository;

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
}
