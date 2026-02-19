package com.taskmanager.backend.service;

import com.taskmanager.backend.dto.TaskDTO;
import com.taskmanager.backend.entities.Task;
import com.taskmanager.backend.enums.TaskCategory;
import com.taskmanager.backend.enums.TaskPriority;
import com.taskmanager.backend.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found..."));
        return convertToDTO(task);
    }

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    @Transactional
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found..."));

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setTaskCategory(taskDTO.getTaskCategory());
        existingTask.setTaskPriority(taskDTO.getTaskPriority());
        existingTask.setCompleted(taskDTO.getCompleted());

        Task updatedTask = taskRepository.save(existingTask);
        return convertToDTO(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found...");
        }
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getTasksByCategory(TaskCategory taskCategory) {
        return taskRepository.findByTaskCategory(taskCategory)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<TaskDTO> getTasksByPriority(TaskPriority taskPriority) {
        return taskRepository.findByTaskPriority(taskPriority)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<TaskDTO> getTasksByCompleted(Boolean completed) {
        return taskRepository.findByCompleted(completed)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public TaskDTO toggleTaskCompletion(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found..."));

        task.setCompleted(!task.getCompleted());
        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setTaskCategory(task.getTaskCategory());
        taskDTO.setTaskPriority(task.getTaskPriority());
        taskDTO.setCompleted(task.getCompleted());
        return taskDTO;
    }

    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setTaskCategory(dto.getTaskCategory());
        task.setTaskPriority(dto.getTaskPriority());
        task.setCompleted(dto.getCompleted());
        return task;
    }
}
