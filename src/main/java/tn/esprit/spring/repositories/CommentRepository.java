package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.User;

import java.util.List;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByUser(User user);
}