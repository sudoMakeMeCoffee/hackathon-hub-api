package com.hackathon_hub.hackathon_hub_api.repository;

import com.hackathon_hub.hackathon_hub_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}