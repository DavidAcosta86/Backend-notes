package com.notesapp.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.notesapp.backend.dto.CategoryDTO;
import com.notesapp.backend.entity.Category;
import com.notesapp.backend.entity.Note;
import com.notesapp.backend.mapper.CategoryMapper;
import com.notesapp.backend.repository.CategoryRepository;
import com.notesapp.backend.repository.NoteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final NoteRepository noteRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository, NoteRepository noteRepository) {
        this.categoryRepository = categoryRepository;
        this.noteRepository = noteRepository;
    }
    
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id).map(category -> {
            Category updated = CategoryMapper.toEntity(categoryDTO);
            updated.setId(category.getId()); 
            return CategoryMapper.toDTO(categoryRepository.save(updated));
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
}

    @Override
    public void deleteCategory(Long id) {
       Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Buscar todas las notas que tienen esta categoría
        List<Note> notesWithCategory = noteRepository.findAllByCategories_Id(id);

        // Eliminar esas notas
        noteRepository.deleteAll(notesWithCategory);

        // Finalmente, eliminar la categoría
        categoryRepository.delete(category);
    }
    

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }


}
