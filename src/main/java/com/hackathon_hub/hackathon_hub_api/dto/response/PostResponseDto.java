package com.hackathon_hub.hackathon_hub_api.dto.response;

import com.hackathon_hub.hackathon_hub_api.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostResponseDto {
    private UUID id;
    private String caption;
    private String imagePath;
    private UserResponseDto createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponseDto fromEntity(Post post){
        return new PostResponseDto(
                post.getId(),
                post.getCaption(),
                post.getImagePath(),
                UserResponseDto.fromEntity(post.getCreatedBy()),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}


