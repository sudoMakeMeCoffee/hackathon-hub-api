package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.dto.request.ChangePasswordRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.SignInRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.request.SignUpRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.SignInResult;
import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.enums.Role;
import com.hackathon_hub.hackathon_hub_api.exception.InvalidPasswordException;
import com.hackathon_hub.hackathon_hub_api.exception.UnauthorizedException;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.Impl.EmailServiceImpl;
import com.hackathon_hub.hackathon_hub_api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {


    public UserResponseDto addUser(SignUpRequestDto requestDto);

    public SignInResult signin(SignInRequestDto requestDto);

    public UserResponseDto checkAuth(HttpServletRequest request);

    public UserResponseDto changePassword(HttpServletRequest request, ChangePasswordRequestDto requestDto) throws Exception;

    public ResponseCookie logout();

}
