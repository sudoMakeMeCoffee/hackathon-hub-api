package com.hackathon_hub.hackathon_hub_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hackathon_hub.hackathon_hub_api.dto.response.TestimonialResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.service.TestimonialService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/testimonial")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllTestimonials() {
        List<TestimonialResponseDto> allTestimonials = testimonialService.getAllTestimonials();

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Testimonials retrieved successfully.",
                allTestimonials);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateTestimonial(
            @PathVariable UUID id,
            @RequestParam("name") String name,
            @RequestParam("feedback") String feedback,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            TestimonialResponseDto updatedTestimonial = testimonialService.updateTestimonial(id, name, feedback, imageFile);

            ApiResponse<Object> response = new ApiResponse<>(
                    true,
                    "Testimonial updated successfully.",
                    updatedTestimonial);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
