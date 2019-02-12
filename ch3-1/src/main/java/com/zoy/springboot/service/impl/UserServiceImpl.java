package com.zoy.springboot.service.impl;

import com.zoy.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * ·用户表user_info的 增删改查
 * Created by zouzp on 2019/2/11.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void createUser(String name, Integer age) {
        jdbcTemplate.update("INSERT INTO user_info(name, age) VALUES (?, ?)", name, age);
    }

    @Override
    public void deleteUser(String name) {
        jdbcTemplate.update("DELETE FROM user_info WHERE name=?", name);
    }

    @Override
    public Integer getAllUsersNum() {
        return jdbcTemplate.queryForObject("SELECT count(1) FROM user_info", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("DELETE FROM user_info");
    }
}
