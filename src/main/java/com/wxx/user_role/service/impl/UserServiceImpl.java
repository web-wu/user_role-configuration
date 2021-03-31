package com.wxx.user_role.service.impl;

import com.wxx.user_role.dao.RoleDao;
import com.wxx.user_role.dao.UserDao;
import com.wxx.user_role.entity.Role;
import com.wxx.user_role.entity.User;
import com.wxx.user_role.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUser();
        for (User user : userList) {
            Long userId = user.getId();
            List<Role> roles = userDao.findRolesByUserId(userId);
            user.setRoleList(roles);
        }
        return userList;
    }

    @Override
    public List<Role> activeRole() {
        List<Role> roleList = roleDao.getRoleList();
        return roleList;
    }

    @Override
    public void addUSer(User user, Long[] roleList) {
        Long userId = userDao.addUser(user);
        userDao.addUser_Role(userId, roleList);
    }

    @Override
    public User login(User user) throws Exception {
        User _user = userDao.login(user);
        return _user;
    }
}
