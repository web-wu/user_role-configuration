package com.wxx.user_role.service;

import com.wxx.user_role.entity.Role;
import com.wxx.user_role.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    List<Role> activeRole();

    void addUSer(User user, Long[] roleList);
}
