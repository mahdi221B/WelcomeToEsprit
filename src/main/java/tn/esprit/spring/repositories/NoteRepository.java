package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.entity.Note;
@Repository

public interface NoteRepository extends JpaRepository<Note, Long> {
}
