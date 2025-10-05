package com.example.todoapp.controller;

import com.example.todoapp.dto.TaskDTO;
import com.example.todoapp.model.Task;
import com.example.todoapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    // DTO Helper
    private TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCategory().getId(),
                task.getCategory().getName()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody TaskDTO req) {
        Task task = taskService.createTask(req.getTitle(), req.getDescription(), req.getCategoryId());
        return toDTO(task);
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return toDTO(taskService.getTaskById(id));
    }

    @GetMapping("/category/{categoryId}")
    public List<TaskDTO> getTasksByCategory(@PathVariable Long categoryId) {
        return taskService.getTasksByCategoryId(categoryId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public TaskDTO updateTaskStatus(@PathVariable Long id, @RequestParam Boolean completed) {
        return toDTO(taskService.updateTaskStatus(id, completed));
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO req) {
        return toDTO(taskService.updateTask(id, req.getTitle(), req.getDescription()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}