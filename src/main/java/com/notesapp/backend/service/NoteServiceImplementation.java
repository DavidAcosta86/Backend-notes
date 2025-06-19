package com.notesapp.backend.service;

import com.notesapp.backend.dto.NoteDTO;
import com.notesapp.backend.entity.Category;
import com.notesapp.backend.entity.Note;
import com.notesapp.backend.mapper.NoteMapper;
import com.notesapp.backend.repository.CategoryRepository;
import com.notesapp.backend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImplementation implements NoteService {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;


    public NoteServiceImplementation(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public NoteDTO createNote(NoteDTO noteDTO) {
    List<Category> categories = categoryRepository.findAllById(noteDTO.getCategoryIds());
    Note note = NoteMapper.toEntity(noteDTO, categories);
    note = noteRepository.save(note);
    return NoteMapper.toDTO(note);
}

    @Override
    public NoteDTO updateNote(Long id, NoteDTO noteDTO) {
    return noteRepository.findById(id).map(note -> {
        List<Category> categories = categoryRepository.findAllById(noteDTO.getCategoryIds());
        // NO crear un nuevo Note, solo modificar el existente
        Note updated = NoteMapper.toEntity(noteDTO, note, categories);
        return NoteMapper.toDTO(noteRepository.save(updated));
    }).orElseThrow(() -> new RuntimeException("Note not found with id " + id));
}

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(NoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getArchivedNotes() {
        return noteRepository.findByIsArchived(true)
                .stream()
                .map(NoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteDTO> getActiveNotes() {
        return noteRepository.findByIsArchived(false)
                .stream()
                .map(NoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDTO getNoteById(Long id) {
        return noteRepository.findById(id)
                .map(NoteMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }

    @Override
    public NoteDTO toggleArchive(Long id) {
        return noteRepository.findById(id).map(note -> {
            note.setArchived(!note.isArchived());
            return NoteMapper.toDTO(noteRepository.save(note));
        }).orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }

    @Override
    
    public List<NoteDTO> getNotesByCategory(Long categoryId) {
        List<Note> notes = noteRepository.findAllByCategories_Id(categoryId);
        return notes.stream().map(NoteMapper::toDTO).toList();
}
}
