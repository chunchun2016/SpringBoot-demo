package com.example.service;

import com.example.domain.User;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
public interface UserService {


    List<User> getUserList();

    void add(User user);

    User getUser(Long id);

    void update(Long id, User user);

    void remove(Long id);
}
