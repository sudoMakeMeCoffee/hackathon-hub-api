package com.hackathon_hub.hackathon_hub_api.dto.response;

import java.util.UUID;

import com.hackathon_hub.hackathon_hub_api.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EventResponseDto {
    private UUID id;
    private String name;
    private String date;
    private String description;
    private String time;
    private String location;
    private String registerLink;

    public static EventResponseDto fromEntity(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getDescription(),
                event.getTime(),
                event.getLocation(),
                event.getRegisterLink());
    }
}