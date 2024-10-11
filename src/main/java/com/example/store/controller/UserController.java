package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        List<User> users = null;
        try {
            users = userService.getAllUsers();
            System.out.println(users);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return ResponseEntity.ok(users);
    }
}
