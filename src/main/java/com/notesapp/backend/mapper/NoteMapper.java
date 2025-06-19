package com.notesapp.backend.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.notesapp.backend.dto.NoteDTO;
import com.notesapp.backend.entity.Category;
import com.notesapp.backend.entity.Note;

public class NoteMapper {
    public static NoteDTO toDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setContent(note.getContent());
        dto.setArchived(note.isArchived());
        List<Category> categories = note.getCategories();
        dto.setCategoryIds(
        categories == null
            ? new ArrayList<>()
            : categories.stream().map(Category::getId).collect(Collectors.toList())
        );
        return dto;
    }
    
   public static Note toEntity(NoteDTO dto, List<Category> categories) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setArchived(dto.isArchived());
        note.setCategories(categories);
        return note;
    }

    public static Note toEntity(NoteDTO dto, Note note, List<Category> categories) {
        
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setArchived(dto.isArchived());
        note.setCategories(categories);
        return note;
    }
}
