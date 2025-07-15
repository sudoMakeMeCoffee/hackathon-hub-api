package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.request.SignInRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.SignUpRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.SignInResult;
import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<Object>> signin(@Valid @RequestBody SignInRequestDto request){
        SignInResult signInResult = authService.signin(request);

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Login successful.",
                signInResult.getUserResponseDto()
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, signInResult.getCookie().toString())
                .body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        ResponseCookie cookie = authService.logout();

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Logged out successfully",
                null
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, String.valueOf(cookie))
                .body(response);
    }


}
