package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.React;
import java.util.List;

@Repository

public interface ReactRepository extends JpaRepository<React, Integer> {
    // @Query("SELECT p FROM Post p WHERE p.sentimentScore = :i OR p.sentimentScore = :i1 OR p.sentimentScore = :i2")
    React findReactsByUserIdAndPostId(Integer userId, Integer postId);
    List<React> findReactsByPostId(Integer postId);
}