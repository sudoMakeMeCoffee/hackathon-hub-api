package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.request.SignInRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.SignUpRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.SignInResult;
import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.exception.UnauthorizedException;
import com.hackathon_hub.hackathon_hub_api.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/add-user")
    public ResponseEntity<ApiResponse<UserResponseDto>> signup(@Valid @RequestBody SignUpRequestDto request){
        UserResponseDto user = authService.addUser(request);

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

    @PostMapping("/check-auth")
    public ResponseEntity<ApiResponse<Object>> checkAuth(HttpServletRequest request) {
        try {
            UserResponseDto user = authService.checkAuth(request);

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Authorized", user)
            );
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, ex.getMessage(), null));
        }
    }


//    @PostMapping("/verify-email")
//    public ResponseEntity<ApiResponse<Object>> verifyEmail(HttpServletRequest request, @RequestBody String code){
//        if(authService.verifyEmail(request, code)){
//
//            ApiResponse<Object> response = new ApiResponse<>(
//                    true,
//                    "Email verified successfully",
//                    null
//            );
//
//            return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.OK);
//        }
//
//        ApiResponse<Object> response = new ApiResponse<>(
//                false,
//                "Incorrect OTP",
//                null
//        );
//
//        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.BAD_REQUEST);
//    }

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
