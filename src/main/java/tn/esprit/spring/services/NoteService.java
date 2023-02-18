package tn.esprit.spring.services;

import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.entity.Note;

import java.util.List;

public interface NoteService {

    public List<Note> RetrieveAllNote();
    public void DeleteNote(Long id);
    public Note RetrieveNoteById(Long id);
    public Note AddNote(Note note);
    public Note UpdateNote(Note note,Long id);
}
