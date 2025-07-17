package com.hackathon_hub.hackathon_hub_api.service.Impl;

import com.hackathon_hub.hackathon_hub_api.dto.request.SubTaskRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.TaskRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.TaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.entity.SubTask;
import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.exception.TaskNotFoundException;
import com.hackathon_hub.hackathon_hub_api.repository.TaskRepository;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Set<User> taskAssignees = new HashSet<>(userRepository.findAllById(taskRequestDto.getAssigneeIds()));

        Task task = new Task();

        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setAssignedUsers(taskAssignees);

        List<SubTask> subTasks = new ArrayList<>();

        for (SubTaskRequestDto subDTO : taskRequestDto.getSubTasks()) {
            SubTask subtask = new SubTask();
            subtask.setTitle(subDTO.getTitle());
            subtask.setDescription(subDTO.getDescription());
            subtask.setTask(task);

            // Fetch subtask assignees and validate they're within task-level assignees
            Set<User> subtaskAssignees = new HashSet<>(userRepository.findAllById(subDTO.getAssigneeIds()));
            if (!taskAssignees.containsAll(subtaskAssignees)) {
                throw new IllegalArgumentException("Subtask assignees must be part of the main task's assignees.");
            }

            subtask.setAssignedUsers(subtaskAssignees);

            subTasks.add(subtask);
        }

        task.setSubtasks(subTasks);

        Task createdTask =  taskRepository.save(task);

        return TaskResponseDto.fromEntity(task);

    }

    @Override
    public TaskResponseDto getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with this id not found."));

        return TaskResponseDto.fromEntity(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }
}
