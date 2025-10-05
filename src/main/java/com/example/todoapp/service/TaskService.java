package com.example.todoapp.service;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.Category;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryService categoryService;

    public TaskService(TaskRepository taskRepository, CategoryService categoryService) {
        this.taskRepository = taskRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public Task createTask(String title, String description, Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        Task task = new Task(title, description != null ? description : "", false, category);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByCategoryId(Long categoryId) {
        categoryService.getCategoryById(categoryId); // verifică existența
        return taskRepository.findByCategoryId(categoryId);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @Transactional
    public Task updateTaskStatus(Long id, Boolean completed) {
        Task task = getTaskById(id);
        if (completed != null) task.setCompleted(completed);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, String title, String description) {
        Task task = getTaskById(id);
        if (title != null) task.setTitle(title);
        if (description != null) task.setDescription(description);
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
