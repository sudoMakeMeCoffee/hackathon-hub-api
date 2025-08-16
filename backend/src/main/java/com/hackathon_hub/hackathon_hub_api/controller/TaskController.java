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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createTask(@RequestBody TaskRequestDto taskRequestDto) {

        TaskResponseDto taskResponseDto = taskService.createTask(taskRequestDto);

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Task created Successfully.",
                taskResponseDto
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllTasks(){
        List<TaskResponseDto> allTasks = taskService.getAllTasks();

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "success",
                allTasks
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getTaskById(@PathVariable UUID id) {
        TaskResponseDto task = taskService.getTaskById(id);
        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "success",
                task
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/subtasks/{subTaskId}/complete")
    public ResponseEntity<ApiResponse<Object>> completeSubTask(
            @PathVariable UUID taskId,
            @PathVariable UUID subTaskId){
        TaskResponseDto task = taskService.markSubTaskComplete(taskId, subTaskId);
        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "success",
                task
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteTaskById(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Task deleted successfully",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
