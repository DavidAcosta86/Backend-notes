package com.notesapp.backend.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notesapp.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
   
    

}
