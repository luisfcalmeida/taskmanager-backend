package com.taskmanager.backend.dto;

import com.taskmanager.backend.enums.TaskCategory;
import com.taskmanager.backend.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private TaskCategory taskCategory;

    private TaskPriority taskPriority;

    private Boolean completed;

}
