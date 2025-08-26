package com.hackathon_hub.hackathon_hub_api.service.Impl;

import java.util.List;
import java.util.UUID;

import com.hackathon_hub.hackathon_hub_api.service.FileService;
import org.springframework.stereotype.Service;

import com.hackathon_hub.hackathon_hub_api.dto.response.TestimonialResponseDto;
import com.hackathon_hub.hackathon_hub_api.repository.TestimonialRepository;
import com.hackathon_hub.hackathon_hub_api.service.TestimonialService;
import com.hackathon_hub.hackathon_hub_api.entity.Testimonials;
import com.hackathon_hub.hackathon_hub_api.exception.TestimonialNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final FileService fileService;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository, FileService fileService) {
        this.testimonialRepository = testimonialRepository;
        this.fileService = fileService;
    }

    @Override
    public List<TestimonialResponseDto> getAllTestimonials() {
        List<Testimonials> allTestimonials = testimonialRepository.findAll();
        return allTestimonials.stream().map(TestimonialResponseDto::fromEntity).toList();
    }

    @Override
    public TestimonialResponseDto updateTestimonial(UUID id, String name, String feedback, MultipartFile imageFile) throws Exception {
        Testimonials existingTestimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new TestimonialNotFoundException("Testimonial with this id not found."));

        existingTestimonial.setName(name);
        existingTestimonial.setFeedback(feedback);

        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = fileService.uploadImage(imageFile, "testimonials");
            existingTestimonial.setImageUrl("/uploads/testimonials/" + imagePath);
        }

        Testimonials updated = testimonialRepository.save(existingTestimonial);
        return TestimonialResponseDto.fromEntity(updated);
    }
}
