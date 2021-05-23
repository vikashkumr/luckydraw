package com.grofers.luckydraw.service;

import com.grofers.luckydraw.entity.User;
import com.grofers.luckydraw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        try {
            userRepository.save(user);
            return "user registered successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "user failed to register";
        }
    }

    public Optional<User> getUserById(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        if(Objects.nonNull(user)) {
            user.setPassword("NA");
        }
        return Optional.ofNullable(user);
    }

    public List<User> getAllUserList() {
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> user.setPassword("NA"));
        return allUsers;
    }

}
