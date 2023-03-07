package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Conversation;
import tn.esprit.spring.entity.User;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
   // @Query("SELECT p FROM Post p WHERE p.sentimentScore = :i OR p.sentimentScore = :i1 OR p.sentimentScore = :i2")
   // Conversation findByParticipants(User user1, User user2);
}