package com.hackathon_hub.hackathon_hub_api.service.Impl;

import com.hackathon_hub.hackathon_hub_api.dto.request.SubTaskRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.TaskRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.SubTaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.TaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.entity.SubTask;
import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.exception.TaskNotFoundException;
import com.hackathon_hub.hackathon_hub_api.repository.TaskRepository;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.EmailService;
import com.hackathon_hub.hackathon_hub_api.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
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

        for (User user : taskAssignees){
            emailService.sendTaskEmail(user.getEmail(), "New Task", user,createdTask);
        }

        return TaskResponseDto.fromEntity(task);

    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();

        return allTasks.stream().map(TaskResponseDto::fromEntity).toList();
    }

    @Override
    public TaskResponseDto getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with this id not found."));

        return TaskResponseDto.fromEntity(task);
    }

    public TaskResponseDto markSubTaskComplete(UUID id, UUID subTaskId){
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task Not Found"));
        SubTask subTask = task.getSubtasks().stream()
                .filter(st -> st.getId().equals(subTaskId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("SubTask Not Found"));

        subTask.setCompleted(true); // ✅ mark as complete

        Task updatedTask = taskRepository.save(task); // persist change (since cascade is enabled)
         return TaskResponseDto.fromEntity(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }
}
