package com.hackathon_hub.hackathon_hub_api.repository;

import com.hackathon_hub.hackathon_hub_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail (String email);
    Optional<User> findByUsername(String username);
    List<User> findByUsernameStartingWithIgnoreCase(String keyword);


}
