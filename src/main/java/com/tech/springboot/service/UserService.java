package com.tech.springboot.service;

import com.tech.springboot.dto.UserResponseDTO;
import com.tech.springboot.entity.User;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers(){

        return buildListUserResponse(userRepository.findAll());
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    private List<UserResponseDTO> buildListUserResponse(List<User> users){
        List<UserResponseDTO> userResponseDTOS = UserMapper.INSTANCE.toDTOs(users);
        return userResponseDTOS;
    }
}
