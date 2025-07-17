package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.service.Impl.EmailServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final EmailServiceImpl emailService;

    public UserController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/user")
    public String hello() {
        return "Hello";
    }

}
