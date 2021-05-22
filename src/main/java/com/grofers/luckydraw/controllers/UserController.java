package com.grofers.luckydraw.controllers;

import com.grofers.luckydraw.entity.User;
import com.grofers.luckydraw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/get_user")
    Optional<User> getUserById(@RequestParam Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/get_all_users")
    List<User> getAllUsers() {
        return userService.getAllUserList();
    }
}
