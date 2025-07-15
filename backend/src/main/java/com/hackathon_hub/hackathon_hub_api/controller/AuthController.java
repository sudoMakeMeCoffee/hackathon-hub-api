package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.request.SignUpRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDto>> signup(@Valid @RequestBody SignUpRequestDto request){
        UserResponseDto user = authService.signup(request);

        ApiResponse<UserResponseDto> response = new ApiResponse<>(
                true,
                "Account created successfully",
                user
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
