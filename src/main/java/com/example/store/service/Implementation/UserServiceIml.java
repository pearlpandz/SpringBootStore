package com.example.store.service.Implementation;

import com.example.store.entity.User;
import com.example.store.payload.LoginRequest;
import com.example.store.repository.UserRepository;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
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

    @Override
    public String verify(LoginRequest payload) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
        if(authentication.isAuthenticated()) {
            String token = jwtService.generateToken(payload.getUsername());
            return token;
        }
        return "Unauthorised!";
    }

}
