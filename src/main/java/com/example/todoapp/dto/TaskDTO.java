package com.example.todoapp.dto;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Long categoryId;
    private String categoryName;

    public TaskDTO() {}

    public TaskDTO(Long id, String title, String description, boolean completed, Long categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}