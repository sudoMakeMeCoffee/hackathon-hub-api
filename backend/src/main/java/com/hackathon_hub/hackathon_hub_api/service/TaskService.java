package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.dto.request.TaskRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.TaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    public TaskResponseDto createTask(TaskRequestDto task);
    public TaskResponseDto getTaskById(UUID id);
}