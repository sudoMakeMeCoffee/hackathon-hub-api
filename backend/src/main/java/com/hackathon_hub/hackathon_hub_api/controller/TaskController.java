package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/task")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

//    @PutMapping("/task")
//    public Task updateTask(@RequestBody Task task) {
//        return taskService.updateTask(task);
//    }

    @GetMapping("/task/{id}")
    public Boolean deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }
}
