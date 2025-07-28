package com.hackathon_hub.hackathon_hub_api.repository;

import com.hackathon_hub.hackathon_hub_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}