package com.tech.springboot.service.impl;

import com.fpt.training.aio.lending.model.UserResponseDto;
import com.tech.springboot.entity.User;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.repository.UserRepository;
import com.tech.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toDTOs(users);
    }
}
