//package com.example.demo.repositories.impl;
//
//import com.example.demo.entity.User;
//import com.example.demo.repositories.UserRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class UserRepositoryImpl implements UserRepository {
//    private final List<User> users = Collections.synchronizedList(new LinkedList<>());
//
//    public UserRepositoryImpl() {
//        for (int i = 0; i < 5; i++) {
//            users.add(User.builder().id(i).age(18 + i).email("Aboba" + i).name("Nikolay " + i).build());
//        }
//    }
//
//    @Override
//    public Optional<User> findById(int id) {
//        return users.stream().filter(u -> u.getId() == id).findFirst();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return Collections.unmodifiableList(users);
//    }
//
//    @Override
//    public User create(User user) {
//        user.setId(users.stream()
//                .max(Comparator.comparing(User::getId))
//                .map(User::getId)
//                .orElse(0) + 1);
//        users.add(user);
//        return user;
//    }
//
//    @Override
//    public void delete(int id) {
//        users.removeIf(u -> u.getId() == id);
//    }
//}