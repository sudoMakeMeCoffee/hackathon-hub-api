package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.entity.Task;
import com.hackathon_hub.hackathon_hub_api.entity.User;

public interface EmailService {
    public void sendAddUserEmail(String to, String subject, String username, String password);
    public void sendTaskEmail(String to, String subject, User user, Task task);

}
