package com.tech.springboot.filter;

import com.tech.springboot.model.RequestInfo;
import com.tech.springboot.service.impl.UserSpringService;
import com.tech.springboot.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {
    private final RequestInfo requestInfo;
    private final UserSpringService userSpringService;
    private final JwtTokenUtils jwtUtil;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException {

        // convert to http request
        log.info("{}: {}", request.getMethod(), request.getRequestURI());

        if (hasAuthorizationBearer(request)) {
            String token = getAccessToken(request);
            if (Boolean.FALSE.equals(jwtUtil.validateToken(token))) {
                filterChain.doFilter(request, response);
                log.error("Request is not valid");
                return;
            }
            setAuthenticationContext(token, request);
            // set info
            requestInfo.extractData(token);
        }
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        log.info("Authorization: {}", header);
        return token;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        String email = jwtUtil.getUsernameFromToken(token);
        UserDetails user = userSpringService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                email, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("(Authorized) Authentication: {}", authentication);
    }
}
