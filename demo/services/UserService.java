package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.entity.payload.UserPayload;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(int id);
    Iterable<User> findUsers();
    User saveUser(UserPayload userPayload);
    void updateUser(int id, UserPayload user);
    void deleteUser(int id);
}
