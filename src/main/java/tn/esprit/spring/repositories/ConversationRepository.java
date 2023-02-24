package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Conversation;
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}