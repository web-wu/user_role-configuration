package com.wxx.user_role.service;

import com.wxx.user_role.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleList();

    void addRole(Role role);
}
