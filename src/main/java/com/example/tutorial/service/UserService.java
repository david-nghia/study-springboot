package com.example.tutorial.service;

import com.example.tutorial.model.User;
import com.example.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User updatedUser) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setUser_password(updatedUser.getUser_password());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
}
