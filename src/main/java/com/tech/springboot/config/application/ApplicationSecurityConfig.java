package com.tech.springboot.config.application;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "application.security")
@Component
@Getter
public class ApplicationSecurityConfig {
    private Set<String> ignorePaths = new HashSet<>();
}
