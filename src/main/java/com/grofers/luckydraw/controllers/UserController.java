package com.grofers.luckydraw.controllers;

import com.grofers.luckydraw.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @PostMapping("/register")
    String registerUser(@RequestBody User user) {

    }

    @GetMapping("/getUser")
    Optional<User> getUserById() {

    }

    @GetMapping("/getUsers")
    List<User> getAllUsers() {

    }
}
