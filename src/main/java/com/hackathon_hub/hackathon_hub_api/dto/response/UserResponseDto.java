package com.hackathon_hub.hackathon_hub_api.dto.response;

import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.enums.Role;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserResponseDto {

    private UUID id;
    private String username;
    private String email;
    private String position;
    private Role role;

    public static UserResponseDto fromEntity(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .position(user.getPosition())
                .build();
    }

}
