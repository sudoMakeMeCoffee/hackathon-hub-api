package com.hackathon_hub.hackathon_hub_api.service.Impl;

import com.hackathon_hub.hackathon_hub_api.dto.response.DashboardOverviewResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.TaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.repository.PostRepository;
import com.hackathon_hub.hackathon_hub_api.repository.TaskRepository;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TaskRepository taskRepository;

    public DashboardServiceImpl(UserRepository userRepository, PostRepository postRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.taskRepository = taskRepository;
    }
    @Override
    public DashboardOverviewResponseDto getDashBoardOverviewData() {
        long totalUsers = userRepository.count();
        long totalTasks = taskRepository.count();
        long totalPosts = postRepository.count();

        // Get 5 most recent tasks (assuming Task has createdAt field)
        List<TaskResponseDto> recentTasks = taskRepository.findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(task -> TaskResponseDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .createdAt(task.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return DashboardOverviewResponseDto.builder()
                .totalUsers(totalUsers)
                .totalTasks(totalTasks)
                .totalPosts(totalPosts)
                .recentTasks(recentTasks)
                .build();
    }
}
