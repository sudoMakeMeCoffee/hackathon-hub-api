package com.hackathon_hub.hackathon_hub_api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SubTaskRequestDto {
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private boolean isCompleted;
    private List<UUID> assigneeIds;
}
