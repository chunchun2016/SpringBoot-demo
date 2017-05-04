package com.example.service;

import com.example.domain.SysUser;

import java.util.List;

/**
 * Created by admin on 17/4/24.
 */
public interface UserService {


    List<SysUser> getUserList();

    void add(SysUser user);

    SysUser getUser(Long id);

    void update(Long id, SysUser user);

    void remove(Long id);

    SysUser findByName(String userName);
}
