package com.tech.springboot.service.impl;

import com.tech.springboot.entity.User;
import com.tech.springboot.enums.ExceptionAlertEnum;
import com.tech.springboot.enums.StatusEnum;
import com.tech.springboot.exception.BusinessException;
import com.tech.springboot.exception.dto.AlertMessage;
import com.tech.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSpringService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findUserByUsername(username);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOpt.get();

        if (user.getStatus() != StatusEnum.ACTIVE) {
            throw new BusinessException("User is non-active",
                    AlertMessage.alert(ExceptionAlertEnum.USER_NON_ACTIVE));
        }
        return user;
    }
}
