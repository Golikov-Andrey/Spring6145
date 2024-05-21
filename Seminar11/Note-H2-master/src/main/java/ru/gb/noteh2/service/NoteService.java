package ru.gb.noteh2.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.noteh2.model.Note;
import ru.gb.noteh2.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getAllNote() {
        return noteRepository.findAll();
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Note updateNoteById(Long id, Note note) {
        note.setId(id);
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}
