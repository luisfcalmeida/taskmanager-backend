package com.taskmanager.backend.repository;

import com.taskmanager.backend.entities.Task;
import com.taskmanager.backend.enums.TaskCategory;
import com.taskmanager.backend.enums.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTaskCategory(TaskCategory category);

    List<Task> findByTaskPriority(TaskPriority priority);

    List<Task> findByCompleted(Boolean completed);

    List<Task> findByTaskCategoryAndTaskPriority(TaskCategory category, TaskPriority priority);

    List<Task> findByTitleContainingIgnoreCase(String title);

}
