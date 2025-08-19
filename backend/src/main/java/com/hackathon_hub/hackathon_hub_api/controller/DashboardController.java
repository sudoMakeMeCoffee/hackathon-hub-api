package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.response.DashboardOverviewResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.TaskResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.repository.PostRepository;
import com.hackathon_hub.hackathon_hub_api.repository.TaskRepository;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getDashBoardOverviewData(){
        DashboardOverviewResponseDto dashboardData = dashboardService.getDashBoardOverviewData();

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Dashboard data fetched successfully",
                dashboardData
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
