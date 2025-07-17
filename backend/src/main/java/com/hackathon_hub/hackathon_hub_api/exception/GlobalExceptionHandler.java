package com.hackathon_hub.hackathon_hub_api.exception;

import com.fasterxml.jackson.databind.ObjectReader;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex){
        ApiResponse<Object> response = new ApiResponse<>(false, "Invalid ID", null);

        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> taskNotFoundExceptionHandler(TaskNotFoundException ex){
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponse<Object>> invalidPasswordExceptionHandler(InvalidPasswordException ex){
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);

        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> usernameNotFondExceptionHandler(UsernameNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "User not found.", null);

        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> badCredentialsExceptionHandler(BadCredentialsException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Invalid credentials", null);

        return new ResponseEntity<ApiResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse<Object>> signatureException(MalformedJwtException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Invalid Token", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<Object>> signatureException(SignatureException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Invalid Token", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<Object>> expiredException(ExpiredJwtException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Expired Token", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        ApiResponse<Object> response = new ApiResponse<>(false, "Email already Exists.", null);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(UnauthorizedException ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Object> response = new ApiResponse<>(false, errors.values().toArray()[0].toString(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> exceptionHandler(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>(false, "Something went wrong: " + ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
