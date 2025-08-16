package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.request.CreatePostRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.PostResponseDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.entity.Post;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    public PostController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    // Get all posts
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();

        // Convert entities to DTOs
        List<PostResponseDto> postDtos = posts.stream()
                .map(PostResponseDto::fromEntity)
                .toList();

        ApiResponse<Object> response = new ApiResponse<>(
                true,
                "Posts fetched successfully.",
                postDtos
        );

        return ResponseEntity.ok(response);
    }

    // Create a new post
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
            @RequestParam("caption") String caption,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails userDetails
    ) throws Exception {

        // Fetch User entity from DB
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CreatePostRequestDto request = new CreatePostRequestDto();
        request.setCaption(caption);
        request.setImage(image);

        Post savedPost = postService.createPost(request, user);

        // Map saved post to DTO
        PostResponseDto postDto = PostResponseDto.fromEntity(savedPost);

        ApiResponse<PostResponseDto> response = new ApiResponse<>(
                true,
                "Post created successfully.",
                postDto
        );

        return ResponseEntity.ok(response);
    }
}
