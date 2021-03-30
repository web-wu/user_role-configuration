package com.wxx.user_role.service.impl;

import com.wxx.user_role.dao.RoleDao;
import com.wxx.user_role.entity.Role;
import com.wxx.user_role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = roleDao.getRoleList();
        return roleList;
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}
