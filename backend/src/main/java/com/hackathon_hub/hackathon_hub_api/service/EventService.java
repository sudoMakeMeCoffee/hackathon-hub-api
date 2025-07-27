package com.hackathon_hub.hackathon_hub_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hackathon_hub.hackathon_hub_api.dto.response.EventResponseDto;

@Service
public interface EventService {
    public List<EventResponseDto> getAllEvents();

    public EventResponseDto updateEvent(UUID id, String name, String date, String description, 
                                       String time, String location, String registerLink);
}