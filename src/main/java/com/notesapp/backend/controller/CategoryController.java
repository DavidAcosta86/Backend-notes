package com.notesapp.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notesapp.backend.dto.CategoryDTO;
import com.notesapp.backend.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

       public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        }


        @GetMapping
        public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
        }

        @PostMapping
        public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
        }

       @DeleteMapping("/{id}")
        public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        }

        @PutMapping("/{id}")
        public CategoryDTO putMethodName(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(id, categoryDTO);
        }


}
