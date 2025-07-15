package com.hackathon_hub.hackathon_hub_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseCookie;

@Getter
@Setter
@Builder
public class SignInResult {
    private ResponseCookie cookie;
    private UserResponseDto userResponseDto;
}
