package com.hackathon_hub.hackathon_hub_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hackathon_hub.hackathon_hub_api.dto.response.TestimonialResponseDto;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface TestimonialService {
    public List<TestimonialResponseDto> getAllTestimonials();

    public TestimonialResponseDto updateTestimonial(UUID id, String name, String feedback, MultipartFile imageFile) throws Exception;
}