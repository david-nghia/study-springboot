package com.tech.springboot.service;

import com.tech.springboot.dto.ListUserResponseDTO;
import com.tech.springboot.dto.Meta;
import com.tech.springboot.dto.UserResponseDTO;
import com.tech.springboot.entity.User;
import com.tech.springboot.mapper.UserMapper;
import com.tech.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ListUserResponseDTO getAllUsers(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by("username"));
        Page<User> userPage = userRepository.findAll(pageRequest);
        return buildListUserResponse(userPage, offset, limit);
    }

    private ListUserResponseDTO buildListUserResponse(Page<User> userPage, int offset, int limit) {
        List<UserResponseDTO> users = UserMapper.INSTANCE.toDTOs(userPage.getContent());
        Meta meta = new Meta(userPage.getNumberOfElements(), limit, offset,
                (int) userPage.getTotalElements());
        return ListUserResponseDTO.builder()
                .users(users)
                .meta(meta)
                .build();
    }
}
