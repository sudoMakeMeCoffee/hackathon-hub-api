package com.hackathon_hub.hackathon_hub_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve uploaded files from "uploads" folder
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
