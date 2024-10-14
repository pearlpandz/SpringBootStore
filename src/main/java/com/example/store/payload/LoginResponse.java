package com.example.store.payload;

import com.example.store.entity.User;

public class LoginResponse {
    private User data;
    private String message;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
