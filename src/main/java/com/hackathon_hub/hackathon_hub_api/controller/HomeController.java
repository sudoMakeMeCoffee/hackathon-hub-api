package com.hackathon_hub.hackathon_hub_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to the platform!";
    }

}
