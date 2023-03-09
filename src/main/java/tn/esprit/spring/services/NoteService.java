package tn.esprit.spring.services;

import com.google.zxing.WriterException;
import tn.esprit.spring.entity.Note;

import java.io.IOException;
import java.util.List;

public interface NoteService {

    public List<Note> RetrieveAllNote();
    public void DeleteNote(Long id);
    public Note RetrieveNoteById(Long id);
    public void AddNote(Note note);
    public Note UpdateNote(Note note,Long id);



    String affectnote(Note n, Long id, int iduser);

    void sendSmsvalide();

    public byte[] genrerateqrit() throws IOException, WriterException;

}
