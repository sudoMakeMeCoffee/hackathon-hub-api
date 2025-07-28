package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.service.Impl.EmailServiceImpl;
import com.hackathon_hub.hackathon_hub_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        ApiResponse<List<UserResponseDto>> response = new ApiResponse<>(
                true,
                "Users retieved successfully.",
                allUsers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getUserById(@PathVariable UUID id) {
        UserResponseDto user = userService.getUserById(id);
        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "success",
                user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> searchUsers(@RequestParam String q) {
        List<User> users = userService.searchUsersByUsername(q);
        List<UserResponseDto> result = users.stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());

        ApiResponse<List<UserResponseDto>> response = new ApiResponse<>(
                true,
                "Users searched successfully",
                result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
