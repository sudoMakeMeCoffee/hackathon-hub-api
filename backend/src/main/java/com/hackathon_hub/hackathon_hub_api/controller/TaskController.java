package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.request.TaskRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.TaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createTask(@RequestBody TaskRequestDto taskRequestDto){

        TaskResponseDto taskResponseDto =  taskService.createTask(taskRequestDto);

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Task created Successfully.",
                taskResponseDto
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
