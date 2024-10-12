package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User userObj = null;
        try {
            userObj = userService.updateUser(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<User> (userObj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user) throws Exception {
        User userObj = user;
        userObj.setUserId(userId);
        User userRes = null;
        try {
            userRes = userService.updateUser(userObj);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<User> (userRes, HttpStatus.CREATED);
    }

    @GetMapping("/list")
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

    @GetMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId) throws Exception {
        User user = null;
        try {
            user = userService.getUser(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<User> (user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int userId) throws Exception {
        User user = null;
        try {
            user = userService.deleteUser(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<User> (user, HttpStatus.OK);
    }
}
