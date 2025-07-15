package com.hackathon_hub.hackathon_hub_api.repository;

import com.hackathon_hub.hackathon_hub_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername (String username);

}
