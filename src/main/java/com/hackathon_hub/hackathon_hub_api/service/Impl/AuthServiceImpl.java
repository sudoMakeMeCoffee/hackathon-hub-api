package com.hackathon_hub.hackathon_hub_api.service.Impl;

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
import com.hackathon_hub.hackathon_hub_api.service.AuthService;
import com.hackathon_hub.hackathon_hub_api.service.UserService;
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
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final EmailServiceImpl emailService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil, EmailServiceImpl emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

//    public boolean verifyEmail(HttpServletRequest request, String code){
//        String jwt = jwtUtil.extractJwtFromCookie(request);
//        String email = jwtUtil.extractEmail(jwt);
//
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        if(Objects.equals(code, user.getCode())){
//            user.setVerified(true);
//            userRepository.save(user);
//            return true;
//        }
//
//        return false;
//
//    }

    @Override
    public UserResponseDto addUser(SignUpRequestDto requestDto) {

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder().username(requestDto.getUsername()).email(requestDto.getEmail()).password(encodedPassword).position(requestDto.getPosition()).role(requestDto.getRole()).build();


        User createdUser = userRepository.save(user);

        emailService.sendAddUserEmail(user.getEmail(), "Welcome to Hackathon Hub Platform", user.getUsername(), requestDto.getPassword());

        return UserResponseDto.fromEntity(createdUser);
    }

    @Override
    public SignInResult signin(SignInRequestDto requestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));

        UserDetails userDetails = userService.loadUserByUsername(requestDto.getEmail());
        UserResponseDto user = userService.getUserByUsername(userDetails.getUsername());
        String token = jwtUtil.generateToken(userDetails);


        ResponseCookie cookie = ResponseCookie.from("jwt", token).httpOnly(true).secure(true)  // set to true in production
                .path("/").maxAge(24 * 60 * 60).sameSite("NoneNone").build();

        return SignInResult.builder().cookie(cookie).userResponseDto(user).build();


    }

    @Override
    public UserResponseDto checkAuth(HttpServletRequest request) {
        String jwt = jwtUtil.extractJwtFromCookie(request);
        String email = jwtUtil.extractEmail(jwt);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UnauthorizedException("User not found"));

        if (jwt == null || !jwtUtil.validateToken(jwt, UserResponseDto.fromEntity(user))) {
            throw new UnauthorizedException("Invalid or missing token");
        }

        return UserResponseDto.fromEntity(user);
    }

    @Override
    public UserResponseDto changePassword(HttpServletRequest request, ChangePasswordRequestDto requestDto) throws Exception {
        String email = jwtUtil.extractEmailFromCookie(request);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);

        return UserResponseDto.fromEntity(user);
    }

    @Override
    public ResponseCookie logout() {
        return ResponseCookie.from("jwt", "").httpOnly(true).secure(true).path("/").maxAge(0).sameSite("None").build();
    }

}
