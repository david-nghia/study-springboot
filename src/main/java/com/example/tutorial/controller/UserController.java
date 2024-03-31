package com.example.tutorial.controller;

import com.example.tutorial.model.User;
import com.example.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping()
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

}
