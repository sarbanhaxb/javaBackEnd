package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
//    Optional<User> findById(int id);
//
//    List<User> findAll();
//
//    User create(User user);
//
//    void delete(int id);
}
