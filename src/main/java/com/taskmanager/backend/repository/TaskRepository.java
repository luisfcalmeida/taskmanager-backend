package com.taskmanager.backend.repository;

import com.taskmanager.backend.entities.Task;
import com.taskmanager.backend.enums.TaskCategory;
import com.taskmanager.backend.enums.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategory(TaskCategory category);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByCompleted(Boolean completed);

    List<Task> findByCategoryAndPriority(TaskCategory category, TaskPriority priority);

    List<Task> findByTitleContainingIgnoreCase(String title);

}
