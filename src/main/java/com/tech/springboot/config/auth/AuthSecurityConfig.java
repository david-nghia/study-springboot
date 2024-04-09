package com.tech.springboot.config.auth;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "application.security")
@Component
@Getter
public class AuthSecurityConfig {
    private Set<String> publicPaths = new HashSet<>();
}
