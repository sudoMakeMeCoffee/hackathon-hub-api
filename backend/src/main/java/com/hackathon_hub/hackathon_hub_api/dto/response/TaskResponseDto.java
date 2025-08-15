package com.hackathon_hub.hackathon_hub_api.dto.response;

import com.hackathon_hub.hackathon_hub_api.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
        private UUID id;
        private String title;
        private String description;
        private LocalDateTime deadline;
        private boolean isCompleted;
        private List<UserResponseDto> taskAssignees;
        private List<SubTaskResponseDto> subTasks;

        public static TaskResponseDto fromEntity(Task task) {

                List<UserResponseDto> users = task.getAssignedUsers().stream()
                                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(),
                                                user.getRole()))
                                .collect(Collectors.toList());

                List<SubTaskResponseDto> subTasks = task.getSubtasks().stream()
                                .map(SubTaskResponseDto::fromEntity).collect(Collectors.toList());

                return new TaskResponseDto(
                                task.getId(),
                                task.getTitle(),
                                task.getDescription(),
                                task.getDeadline(),
                                task.isCompleted(),
                                users,
                                subTasks

                );
        }

}
