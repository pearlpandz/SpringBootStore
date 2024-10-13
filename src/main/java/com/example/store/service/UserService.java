package com.example.store.service;

import com.example.store.entity.User;
import com.example.store.payload.LoginRequest;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(int userId);
    public User updateUser(User user);
    public User deleteUser(int userId);
    public String verify(LoginRequest payload);
}


// If we put something in service, we should right implementation @override function