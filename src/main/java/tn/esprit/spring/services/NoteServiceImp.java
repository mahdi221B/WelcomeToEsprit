package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.repositories.NoteRepository;
import tn.esprit.spring.repositories.NoteRepository;

import java.util.List;

@Service
public class NoteServiceImp implements  NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<Note> RetrieveAllNote() {
        return noteRepository.findAll()  ;

    }

    @Override
    public void DeleteNote(Long id) {
        noteRepository.delete(noteRepository.findById(id).get());

    }

    @Override
    public Note RetrieveNoteById(Long id) {
        return  noteRepository.findById(id).get();
    }

    @Override
    public Note AddNote(Note note) {
        return noteRepository.save(note);

    }

    @Override
    public Note UpdateNote(Note note, Long id) {
        note.setId(id);
        return noteRepository.save(note);
    }
}
