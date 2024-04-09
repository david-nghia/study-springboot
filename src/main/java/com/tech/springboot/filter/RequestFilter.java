package com.tech.springboot.filter;

import com.tech.springboot.model.RequestInfo;
import com.tech.springboot.service.MyUserDetailsService;
import com.tech.springboot.until.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final RequestInfo requestInfo;


    private final MyUserDetailsService myUserDetailsService;


    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (hasAuthorizationBearer(request)) {
            String token = getAccessToken(request);
            if (Boolean.FALSE.equals(jwtUtil.validateToken(token))) {
                filterChain.doFilter(request, response);
                return;
            }
            setAuthenticationContext(token, request);
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
        return header.split(" ")[1].trim();
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        String username = jwtUtil.getUsernameFromToken(token);
        UserDetails user = myUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
