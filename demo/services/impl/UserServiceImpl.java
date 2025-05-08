package com.example.demo.services.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.payload.UserPayload;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public Optional<User> findUser(int id) {
        if (id == -1)
            return Optional.empty();
        return repository.findById(id);
    }

    @Override
    public Iterable<User> findUsers() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User saveUser(UserPayload userPayload) {
        return repository.save(mapper.toEntity(userPayload));
    }

    @Transactional
    @Override
    public void updateUser(int id, UserPayload payload) {
        repository.findById(id).ifPresent(user -> {
            mapper.updateEntity(user, payload);
        });
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }
}
