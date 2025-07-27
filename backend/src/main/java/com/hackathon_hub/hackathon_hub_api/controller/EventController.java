package com.hackathon_hub.hackathon_hub_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hackathon_hub.hackathon_hub_api.dto.response.EventResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.service.EventService;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllEvents() {
        List<EventResponseDto> allEvents = eventService.getAllEvents();

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Events retrieved successfully.",
                allEvents);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateEvent(
            @PathVariable UUID id,
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "time", required = false) String time,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "registerLink", required = false) String registerLink) {
        try {
            EventResponseDto updatedEvent = eventService.updateEvent(id, name, date, description, time, location, registerLink);

            ApiResponse<Object> response = new ApiResponse<>(
                    true,
                    "Event updated successfully.",
                    updatedEvent);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Object> response = new ApiResponse<>(
                    false,
                    "Failed to update event: " + e.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}