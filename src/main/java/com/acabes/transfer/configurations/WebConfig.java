package com.acabes.transfer.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow all endpoints
                .allowedOrigins("*") // allow all origins
                .allowedMethods("*") // allow all HTTP methods (GET, POST, etc.)
                .allowedHeaders("*") // allow all headers
                .allowCredentials(false)
                .maxAge(3600); // cache preflight response for 1 hour
    }
}
