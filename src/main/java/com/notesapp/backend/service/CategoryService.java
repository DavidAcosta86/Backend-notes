package com.notesapp.backend.service;

import java.util.List;
import com.notesapp.backend.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
}
