package com.hackathon_hub.hackathon_hub_api.service.impl;

import com.hackathon_hub.hackathon_hub_api.dto.request.CreatePostRequestDto;
import com.hackathon_hub.hackathon_hub_api.entity.Post;
import com.hackathon_hub.hackathon_hub_api.entity.User;
import com.hackathon_hub.hackathon_hub_api.repository.PostRepository;
import com.hackathon_hub.hackathon_hub_api.service.FileService;
import com.hackathon_hub.hackathon_hub_api.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FileService fileService;

    public PostServiceImpl(PostRepository postRepository, FileService fileService) {
        this.postRepository = postRepository;
        this.fileService = fileService;
    }

    @Override
    public Post createPost(CreatePostRequestDto request, User user) throws Exception {
        String imagePath = null;

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            imagePath = fileService.uploadImage(request.getImage(), "posts");
        }

        Post post = Post.builder()
                .caption(request.getCaption())
                .imagePath(imagePath)
                .createdBy(user) // set creator
                .approved(true) // default false
                .build();

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }




}
