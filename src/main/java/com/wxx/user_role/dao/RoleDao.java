package com.wxx.user_role.dao;

import com.wxx.user_role.entity.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoleList();
    void addRole(Role role);
}
