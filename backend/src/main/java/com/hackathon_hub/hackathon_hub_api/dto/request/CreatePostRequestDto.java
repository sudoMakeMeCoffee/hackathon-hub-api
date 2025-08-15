package com.hackathon_hub.hackathon_hub_api.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {
    private String caption;
    private MultipartFile image;


}
