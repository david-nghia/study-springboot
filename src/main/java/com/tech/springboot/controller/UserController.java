package com.tech.springboot.controller;

import com.tech.springboot.model.User;
import com.tech.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
