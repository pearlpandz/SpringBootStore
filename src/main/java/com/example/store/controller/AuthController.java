package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.payload.LoginRequest;
import com.example.store.payload.LoginResponse;
import com.example.store.repository.UserRepository;
import com.example.store.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        User userObj = null;
        try {
            userObj = userService.updateUser(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<User>(userObj, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest payload, HttpServletResponse response) {
        LoginResponse res = new LoginResponse();

        try {
            String token = userService.verify(payload);
            User user = userRepository.findByUsername(payload.getUsername());

            res.setData(user);

            // Create a cookie with the token
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true); // Prevent JavaScript access
            cookie.setMaxAge(3600); // Set cookie expiration time
            cookie.setPath("/"); // Set cookie path

            // Add the cookie to the response
            response.addCookie(cookie);

            res.setMessage("Successfully LoggedIn!");

            return ResponseEntity.ok(res);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
