package com.acabes.transfer.configurations;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignCustomConfig {

    @Bean
    public RequestInterceptor ngrokHeaderInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("ngrok-skip-browser-warning", "true");
            }
        };
    }
}
