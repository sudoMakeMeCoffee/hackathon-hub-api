package com.hackathon_hub.hackathon_hub_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDto {

    @NotBlank(message = "New Password is required.")
    @Size(min = 8, message = "New Password must be at least 8 characters long.")
    private String newPassword;
}
