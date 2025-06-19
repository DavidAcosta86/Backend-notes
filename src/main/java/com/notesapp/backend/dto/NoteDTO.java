package com.notesapp.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class NoteDTO {
    private Long id;          
    private String title;
    private String content;
    private boolean archived;
    private List<Long> categoryIds; }