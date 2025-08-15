package com.hackathon_hub.hackathon_hub_api.controller;

import com.hackathon_hub.hackathon_hub_api.dto.request.CreatePostRequestDto;
import com.hackathon_hub.hackathon_hub_api.dto.response.common.ApiResponse;
import com.hackathon_hub.hackathon_hub_api.entity.Post;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Post>> createPost(
            @RequestParam("caption") String caption,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @AuthenticationPrincipal User user // assuming Spring Security
    ) throws Exception {
        CreatePostRequestDto request = new CreatePostRequestDto();
        request.setCaption(caption);
        request.setImage(image);

        Post savedPost = postService.createPost(request, user);

        ApiResponse<Post> response = new ApiResponse<>(
                true,
                "Post created successfully.",
                savedPost
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
