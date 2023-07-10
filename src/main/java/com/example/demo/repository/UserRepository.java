package com.example.demo.repository;

import com.example.demo.controller.dto.User;

public interface UserRepository {
    User findById(int id);

    boolean existsByUserNameAndLastName(User user);

    int save(User user);
    int count();
}
