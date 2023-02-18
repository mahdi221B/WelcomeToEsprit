package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Note;
import tn.esprit.spring.services.NoteService;
import tn.esprit.spring.services.NoteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Note")
public class NoteController {
    private final NoteService noteService;
    @PostMapping("/add")
    @ResponseBody
    public Note addNote(@RequestBody Note note){
        return noteService.AddNote(note);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Note updateNote(@RequestBody Note note, @PathVariable("id") Long id){
        return noteService.UpdateNote(note,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteNote(@PathVariable("id") Long id){
        noteService.DeleteNote(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Note getNoteById(@PathVariable("id") Long id){
        return noteService.RetrieveNoteById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Note> getAllNote(){
        return noteService.RetrieveAllNote();
    }
}
