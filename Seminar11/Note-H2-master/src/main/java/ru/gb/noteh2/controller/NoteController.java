package ru.gb.noteh2.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.noteh2.service.NoteService;
import ru.gb.noteh2.model.Note;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    private final Counter addNoteCounter = Metrics.counter("add_note_count");

    @PostMapping("/create")
    public ResponseEntity<Note> CreateNote(@RequestBody Note note){

        addNoteCounter.increment();

        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Note>> getAllNote(){
        return new ResponseEntity<>(noteService.getAllNote(),HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id){
        return new ResponseEntity<>(noteService.getNoteById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note){
        return new ResponseEntity<>(noteService.updateNoteById(id,note),HttpStatus.OK);
    }

    @PostMapping("delete/{id}")
     public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        noteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
