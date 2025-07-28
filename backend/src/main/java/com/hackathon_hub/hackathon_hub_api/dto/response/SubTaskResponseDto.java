package com.hackathon_hub.hackathon_hub_api.dto.response;

import com.hackathon_hub.hackathon_hub_api.entity.SubTask;
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
public class SubTaskResponseDto {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private boolean isCompleted;
    private List<UserResponseDto> subTaskAssignees;

    public static SubTaskResponseDto fromEntity(SubTask subTask) {

        List<UserResponseDto> assignees = subTask.getAssignedUsers().stream()
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());

        return new SubTaskResponseDto(
                subTask.getId(),
                subTask.getTitle(),
                subTask.getDescription(),
                subTask.getCreatedAt(),
                subTask.getDeadline(),
                subTask.isCompleted(),
                assignees);
    }
}
