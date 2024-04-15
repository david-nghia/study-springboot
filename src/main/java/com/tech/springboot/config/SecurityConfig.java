package com.tech.springboot.config;

import com.tech.springboot.config.auth.AuthSecurityConfig;
import com.tech.springboot.filter.RequestFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.*;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.OPTIONS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String PATH_PATTERN_DELIMITER = "::";
    private final RequestFilter requestFilter;
    private final AuthSecurityConfig authSecurityConfig;
    private final String AUTH_ROLE = "User";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.exceptionHandling(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable).anonymous(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable).cors(populateCorsConfiguration())
                .sessionManagement(populateSessionManagement())
                .authorizeHttpRequests(populateAuthorizeHttpRequests())
                .exceptionHandling(exceptionHandling()).build();
    }

    private Customizer<ExceptionHandlingConfigurer<HttpSecurity>> exceptionHandling() {
        return handling -> handling.authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        });
    }

    private Customizer<CorsConfigurer<HttpSecurity>> populateCorsConfiguration() {
        return cors -> cors.configurationSource(populateCorsConfigurationSource());
    }

    private CorsConfigurationSource populateCorsConfigurationSource() {
        return request -> {
            var corsConfiguration = new CorsConfiguration();
            corsConfiguration.applyPermitDefaultValues();
            corsConfiguration.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));
            corsConfiguration.setAllowedMethods(
                    List.of(GET.name(), POST.name(), PUT.name(), PATCH.name(), DELETE.name(),
                            OPTIONS.name()));
            return corsConfiguration;
        };
    }

    private Customizer<SessionManagementConfigurer<HttpSecurity>> populateSessionManagement() {
        return sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS);
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> populateAuthorizeHttpRequests() {
        return auth -> {
            auth.requestMatchers("/error").permitAll();
            populateIgnorePaths().forEach(
                    (method, url) -> auth.requestMatchers(HttpMethod.valueOf(method),
                            url.toArray(String[]::new)).permitAll());
//             auth.requestMatchers(POST, "/v1/abcxyz").permitAll();
//             auth.requestMatchers(GET, "/api/v1/courses").permitAll();
            auth.anyRequest().hasAuthority(AUTH_ROLE);
        };
    }

    private Map<String, List<String>> populateIgnorePaths() {
        var ignorePaths = new HashMap<String, List<String>>();
        for (var ignorePath : authSecurityConfig.getPublicPaths()) {
            var splitIgnorePath = ignorePath.split(PATH_PATTERN_DELIMITER);
            var ignorePathMethod = splitIgnorePath[0];
            var ignorePathUrl = splitIgnorePath[1];

            var httpMethodIgnorePaths = ignorePaths.getOrDefault(ignorePathMethod,
                    new ArrayList<>());
            httpMethodIgnorePaths.add(ignorePathUrl);
            ignorePaths.put(ignorePathMethod, httpMethodIgnorePaths);
        }
        return ignorePaths;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
