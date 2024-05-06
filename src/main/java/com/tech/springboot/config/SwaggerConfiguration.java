package com.tech.springboot.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi pubApi() {
        return GroupedOpenApi.builder()
                .group("study-springboot-backend")
                .packagesToScan("com.tech.springboot")
                .build();
    }

}
