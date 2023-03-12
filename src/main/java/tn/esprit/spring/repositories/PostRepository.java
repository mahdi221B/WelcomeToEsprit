package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUser(User user);
    List<Post> findPostsByUser(User user);
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.creationDate between :sixHoursAgo AND :now ")
    List<Post> findPostByUserIDAndCreatedAtBetween(@Param("userId") Integer userId, @Param("sixHoursAgo") LocalDateTime sixHoursAgo, @Param("now") LocalDateTime now);
    @Query("SELECT p FROM Post p WHERE p.sentimentScore = :i OR p.sentimentScore = :i1 OR p.sentimentScore = :i2")
    List<Post> findBySentimentScoreBetweenAndCreatedAtAfter(@Param("i") Integer i, @Param("i1") Integer i1, @Param("i2") Integer i2);

}