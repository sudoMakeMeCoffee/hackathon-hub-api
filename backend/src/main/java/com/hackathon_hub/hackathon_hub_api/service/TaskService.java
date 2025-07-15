package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask (Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasks () {
        return taskRepository.findAll();
    }

//    public Task updateTask(Task task) {
//    }

    public Boolean deleteTask (int id) {
        taskRepository.deleteById((id));
        return true;
    }
}