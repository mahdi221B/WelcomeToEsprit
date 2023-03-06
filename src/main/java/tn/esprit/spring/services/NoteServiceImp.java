package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.entity.Project;
import tn.esprit.spring.repositories.NoteRepository;
import tn.esprit.spring.repositories.ProjectRepository;
import com.google.zxing.WriterException;


import java.io.IOException;
import java.util.List;

@Service
public class NoteServiceImp implements  NoteService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    QRCodeGenerator qrCodeGenerator ;
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

    @Override
    public Note affectnote(Note n, Long id)  {
        Project p = projectRepository.findById(id).get();
        p.getNotes().add(n);
        n.setProject(p);
        noteRepository.save(n);
        projectRepository.save(p);
        //qrCodeGenerator.generateQRCodeImage( "the best team is " ,255,255);
        return n;
    }

    @Override
    public byte[] genrerateqr() throws IOException, WriterException {
        return qrCodeGenerator. generateQRCodeImage( "the best team is " ,255,255);
    }


// team 1 sms l chef d'equipe y9olou rak enti loula





}
