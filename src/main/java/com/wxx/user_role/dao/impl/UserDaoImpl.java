package com.wxx.user_role.dao.impl;

import com.wxx.user_role.dao.UserDao;
import com.wxx.user_role.entity.Role;
import com.wxx.user_role.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUser() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public List<Role> findRolesByUserId(Long id) {
        List<Role> roles = jdbcTemplate.query("select * from sys_user_role ur, sys_role r where ur.roleId = r.id and ur.userId = ?", new BeanPropertyRowMapper<Role>(Role.class), id);
        return roles;
    }

    @Override
    public Long addUser(final User user) {
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始jdbc完成有个PreparedStatement的组建
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getPhoneNum());
                return preparedStatement;
            }
        };
        //创建keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator,keyHolder);
        //获得生成的主键
        long userId = keyHolder.getKey().longValue();
        return userId; //返回当前保存用户的id 该id是数据库自动生成的
//        int Longda = jdbcTemplate.update("insert into sys_user values (?, ?, ?, ?, ?)", null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());
    }

    @Override
    public void addUser_Role(Long userId, Long[] roleIds) {
        for (Long roleId : roleIds) {
            jdbcTemplate.update("insert into sys_user_role values (?, ?)", userId, roleId);
        }
    }

    @Override
    public User login(User user) throws Exception {
        User _user = jdbcTemplate.queryForObject("select * from sys_user where username = ? and password = ?", new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
        return _user;
    }
}
