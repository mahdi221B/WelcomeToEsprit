package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.User;

import java.util.List;

@Repository

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUser(User user);
}