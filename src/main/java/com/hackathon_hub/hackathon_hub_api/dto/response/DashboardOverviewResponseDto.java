package com.hackathon_hub.hackathon_hub_api.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardOverviewResponseDto {
    private long totalUsers;
    private long totalTasks;
    private long totalPosts;

    private List<TaskResponseDto> recentTasks;

}
