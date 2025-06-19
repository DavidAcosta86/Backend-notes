package com.notesapp.backend.controller;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notesapp.backend.dto.NoteDTO;
import com.notesapp.backend.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDTO> getAllActiveNotes() {
        return noteService.getActiveNotes();
    }

    @GetMapping("/archived")
    public List<NoteDTO> getAllArchivedNotes() {
        return noteService.getArchivedNotes();
    }

     @GetMapping("/all")
    public List<NoteDTO> getAllNotes() {
        return noteService.getAllNotes(); 
    }
    @PostMapping
    public NoteDTO createNote(@Valid @RequestBody NoteDTO noteDTO) {
        return noteService.createNote(noteDTO);
    }

    @PutMapping("/{id}")
    public NoteDTO updateNote(@PathVariable Long id, @Valid @RequestBody NoteDTO noteDTO) {
        return noteService.updateNote(id, noteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

    @PatchMapping("/{id}/archive")
    public NoteDTO toggleArchive(@PathVariable Long id) {
        return noteService.toggleArchive(id);
    }

    @GetMapping("/by-category/{categoryId}")
public List<NoteDTO> getNotesByCategory(@PathVariable Long categoryId) {
    return noteService.getNotesByCategory(categoryId); // nunca null
}
}
