package com.notesapp.backend.mapper;

import com.notesapp.backend.dto.CategoryDTO;
import com.notesapp.backend.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
     
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
    Category category = new Category();
    category.setId(dto.getId());
    category.setName(dto.getName());
    
   
    return category;
}
}