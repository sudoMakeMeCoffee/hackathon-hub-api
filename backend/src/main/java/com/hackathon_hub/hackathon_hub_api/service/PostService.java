package com.hackathon_hub.hackathon_hub_api.service;

import com.hackathon_hub.hackathon_hub_api.dto.request.CreatePostRequestDto;
import com.hackathon_hub.hackathon_hub_api.entity.Post;
import com.hackathon_hub.hackathon_hub_api.entity.User;

public interface PostService {
    Post createPost(CreatePostRequestDto request, User user) throws Exception;
}
