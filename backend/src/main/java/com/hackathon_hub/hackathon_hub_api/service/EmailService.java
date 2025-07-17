package com.hackathon_hub.hackathon_hub_api.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String username, String password);
}
