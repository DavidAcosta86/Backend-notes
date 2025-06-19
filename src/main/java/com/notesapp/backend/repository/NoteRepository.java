package com.notesapp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notesapp.backend.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
    List<Note> findByIsArchived(boolean isArchived);
    List<Note> findAllByCategories_Id(Long categoryId);
}
