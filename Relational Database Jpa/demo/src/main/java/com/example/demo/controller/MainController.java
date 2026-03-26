package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UserRepository userRepo;

    public MainController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // CREATE
    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userRepo.save(user);
    }

    // READ
    @GetMapping("/users")
    public List<User> getAll() {
        return userRepo.findAll();
    }
}