package com.example.service.impl;

import com.example.domain.SysUser;
import com.example.domain.SysUserMapper;
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
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> getUserList() {
        return userMapper.findAll();
    }

    @Override
    public void add(SysUser user) {
        userMapper.insert(user.getName(), user.getAge(), user.getEmail());
    }

    @Override
    public SysUser getUser(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void update(Long id, SysUser user) {
        userMapper.update(id, user.getName(), user.getAge(), user.getEmail());
    }

    @Override
    public void remove(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public SysUser findByName(String userName) {
        return userMapper.findByName(userName);
    }
}
