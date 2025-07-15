package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.dto.request.SignUpRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.enums.Role;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
}
