package com.hackathon_hub.hackathon_hub_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
    @Email(message = "Enter a valid email.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}
