package com.tech.springboot.model;

import com.tech.springboot.until.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;


@Component
@Getter
@Setter
@Slf4j
public class RequestInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -4919846569047835219L;

    @Autowired
    private JwtUtil jwtUtil;
    private String token;
    private String username;

    public void extractData(final String token){
        if (!StringUtils.hasText(token)) {
            log.warn("Token is empty");
        }

        this.token = token;
        username = jwtUtil.getUsernameFromToken(token);
        log.info("Username in token: {}", username);
    }

}
