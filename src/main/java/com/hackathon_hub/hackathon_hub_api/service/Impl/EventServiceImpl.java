package com.hackathon_hub.hackathon_hub_api.service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hackathon_hub.hackathon_hub_api.dto.response.EventResponseDto;
import com.hackathon_hub.hackathon_hub_api.repository.EventRepository;
import com.hackathon_hub.hackathon_hub_api.service.EventService;
import com.hackathon_hub.hackathon_hub_api.entity.Event;
import com.hackathon_hub.hackathon_hub_api.exception.EventNotFoundException;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        return allEvents.stream().map(EventResponseDto::fromEntity).toList();
    }

    @Override
    public EventResponseDto updateEvent(UUID id, String name, String date, String description, 
                                       String time, String location, String registerLink) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with this id not found."));

        existingEvent.setName(name);
        existingEvent.setDate(date);
        existingEvent.setDescription(description);
        existingEvent.setTime(time);
        existingEvent.setLocation(location);
        existingEvent.setRegisterLink(registerLink);

        Event updatedEvent = eventRepository.save(existingEvent);
        return EventResponseDto.fromEntity(updatedEvent);
    }
}