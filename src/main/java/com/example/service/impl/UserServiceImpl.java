package com.example.service.impl;

import com.example.domain.User;
import com.example.domain.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    @Override
    public void add(User user) {
        userMapper.insert(user.getName(), user.getAge());
    }

    @Override
    public User getUser(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void update(Long id, User user) {
        userMapper.update(id, user.getName(), user.getAge());
    }

    @Override
    public void remove(Long id) {
        userMapper.deleteById(id);
    }
}
