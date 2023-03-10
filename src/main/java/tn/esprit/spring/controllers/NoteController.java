package tn.esprit.spring.controllers;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.services.NoteService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Note")
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/add")
    @ResponseBody
    public void addNote(@RequestBody Note note) {
         noteService.AddNote(note);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Note updateNote(@RequestBody Note note, @PathVariable("id") Long id) {
        return noteService.UpdateNote(note, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteNote(@PathVariable("id") Long id) {
        noteService.DeleteNote(id);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Note getNoteById(@PathVariable("id") Long id) {
        return noteService.RetrieveNoteById(id);
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<Note> getAllNote() {
        return noteService.RetrieveAllNote();
    }

    @PutMapping("/givemarktoproject/{id}/{iduser}")
    public String  givemarktoproject(@RequestBody Note n, @PathVariable("id") Long id, @PathVariable("iduser") int iduser) {
         return  noteService.affectnote(n, id,iduser);

    }
    @GetMapping("/qrit")
    @ResponseBody
    public byte[] getNoteById() throws IOException, WriterException {
        //  noteService.sendSmsvalide();
        return noteService.genrerateqrit()  ;
    }

    @GetMapping("/avrageit/{filter}")
    public String  statit( @PathVariable("filter") int filter) {
        return noteService.statistics(filter)  ;
    }
}




