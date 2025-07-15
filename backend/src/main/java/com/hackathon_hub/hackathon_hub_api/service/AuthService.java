package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.dto.request.SignInRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.SignUpRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.SignInResult;
import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.enums.Role;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.utils.JwtUtil;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDto signup(SignUpRequestDto requestDto){
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User user = User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .role(Role.EDITOR)
                .build();

        User createdUser = userRepository.save(user);

        return UserResponseDto.fromEntity(createdUser);
    }

    public SignInResult signin(SignInRequestDto requestDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(requestDto.getEmail());
        UserResponseDto user = userService.getUserByUsername(userDetails.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        

        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)  // set to true in production
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Lax")
                .build();

        return SignInResult.builder()
                .cookie(cookie)
                .userResponseDto(user)
                .build();


    }
}
