package tn.esprit.spring.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.CandidatType;
import tn.esprit.spring.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

    List<Interview> findAllByCandidatTypeAndScoreGreaterThan(CandidatType candidatType, int score);


    @Query("SELECT i FROM Interview i ORDER BY i.score DESC")
    List<Interview> findAllSortedByScoreDesc();

    @Query(value = "SELECT * FROM interview ORDER BY score DESC LIMIT 1", nativeQuery = true)
    Interview findInterviewWithMaxScore();




    @Query("SELECT i FROM Interview i WHERE i.score = (SELECT MAX(j.score) FROM Interview j)")
    Interview findBestInterviewee();

    List<Interview> findAllByOrderByScoreDesc();

    @Query("SELECT i.interviewee FROM Interview i")
    List<String> getAllInterviewees();


    @Query("SELECT i.interviewee FROM Interview i WHERE i.score > 16.0 AND i.score < 20.0")
    List<String> getSelectedInterviewees();
}


