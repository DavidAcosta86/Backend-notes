package com.notesapp.backend.service;

import java.util.List;
import com.notesapp.backend.dto.NoteDTO;


public interface NoteService {
    NoteDTO createNote(NoteDTO noteDTO);
    NoteDTO updateNote(Long id, NoteDTO noteDTO);
    void deleteNote(Long id);
    List<NoteDTO> getAllNotes();
    List<NoteDTO> getArchivedNotes();
    List<NoteDTO> getActiveNotes();
    NoteDTO getNoteById(Long id);
    NoteDTO toggleArchive(Long id);
    List<NoteDTO> getNotesByCategory(Long categoryId);
}
