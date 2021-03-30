package com.wxx.user_role.dao.impl;

import com.wxx.user_role.dao.RoleDao;
import com.wxx.user_role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public void addRole(Role role) {
        int addLong = jdbcTemplate.update("insert into sys_role values (?, ?, ?)", null, role.getRoleName(), role.getRoleDesc());
    }
}
