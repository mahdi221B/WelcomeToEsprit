package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByMcqsIdMcq(Long mcqs_idMcq) ;
    @Query("SELECT q FROM Question q WHERE q.enonce LIKE %:keyword% OR q.option1 LIKE %:keyword% OR q.option2 LIKE %:keyword% OR q.option3 LIKE %:keyword% OR q.correctAnswer LIKE %:keyword%")
    List<Question> findByKeywords(@Param("keyword") String keywords);

    Question findByIdQuestion(int idQuestion);
}
