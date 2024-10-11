package com.example.store.service;

import com.example.store.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(int userId);
    public User updateUser(User user);
    public User deleteUser(int userId);
}
