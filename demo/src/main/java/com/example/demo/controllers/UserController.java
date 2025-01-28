package com.example.demo.controllers;

import com.example.demo.entity.UserEntity;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userServices.createUser(user);
    }
}
