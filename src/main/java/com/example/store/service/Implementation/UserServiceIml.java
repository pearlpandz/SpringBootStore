package com.example.store.service.Implementation;

import com.example.store.entity.User;
import com.example.store.repository.UserRepository;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(encoder.encode((user.getPassword())));
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(int userId) {
        User deletedUser = null;
        try {
            deletedUser = userRepository.findById(userId).orElse(null);
            if(deletedUser == null) {
                throw new Exception("Something went wrong");
            } else {
                userRepository.deleteById(userId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return deletedUser;
    }
}
