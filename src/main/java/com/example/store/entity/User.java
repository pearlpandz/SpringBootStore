package com.example.store.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private int userId;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    public User() {
        super();
    }

    public User(int userId, String name, int age) {
        super();
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

