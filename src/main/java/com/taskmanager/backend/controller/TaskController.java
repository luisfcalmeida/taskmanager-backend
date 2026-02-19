package com.taskmanager.backend.controller;

import com.taskmanager.backend.dto.TaskDTO;
import com.taskmanager.backend.enums.TaskCategory;
import com.taskmanager.backend.enums.TaskPriority;
import com.taskmanager.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
// TODO - Replace origins with the correct URL per environment
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    // GET /api/tasks List all tasks

    /**
     * GET /api/tasks
     * List all tasks
     */
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/{id}
     * Search task by ID
     */
    // GET /api/tasks/{id} - Search task with ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        try {
            TaskDTO task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /api/tasks
     * Create new task
     */
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * PUT /api/tasks/{id}
     * Update task
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        try {
            TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/tasks/{id}
     * Delete task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * PATCH /api/tasks/{id}/toggle
     * Switch status
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TaskDTO> toggleTaskCompletion(@PathVariable Long id) {
        try {
            TaskDTO task = taskService.toggleTaskCompletion(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/tasks/category/{category}
     * Filter by category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<TaskDTO>> getTasksByCategory(@PathVariable TaskCategory category) {
        List<TaskDTO> tasks = taskService.getTasksByCategory(category);
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/priority/{priority}
     * Filter by priority
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskDTO>> getTasksByPriority(@PathVariable TaskPriority priority) {
        List<TaskDTO> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/completed/{completed}
     * Filter by status
     */
    @GetMapping("/completed/{completed}")
    public ResponseEntity<List<TaskDTO>> getTasksByCompleted(@PathVariable Boolean completed) {
        List<TaskDTO> tasks = taskService.getTasksByCompleted(completed);
        return ResponseEntity.ok(tasks);
    }

}
