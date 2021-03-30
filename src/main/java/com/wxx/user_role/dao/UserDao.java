package com.wxx.user_role.dao;

import com.wxx.user_role.entity.Role;
import com.wxx.user_role.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUser();

    List<Role> findRolesByUserId(Long id);

    Long addUser(User user);

    void addUser_Role(Long userId, Long[] roleIds);
}
