package com.zoy.springboot.service.impl;

import com.zoy.springboot.service.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zouzp on 2019/2/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private IUserService userService;

    @Before
    public void setUp() {
        // ·前期：清空用户表
        userService.deleteAllUsers();
    }

    @Test
    public void test() {
        // ·增：先插入5条数据
        userService.createUser("user_01",1);
        userService.createUser("user_02",2);
        userService.createUser("user_03",3);
        userService.createUser("user_04",4);
        userService.createUser("user_05",5);

        // ·查，应该有5个用户
        Assert.assertEquals(5, userService.getAllUsersNum().intValue());

        // ·删：删除用户名为 user_03的用户
        userService.deleteUser("user_03");

        // ·查，应该有4个用户
        Assert.assertEquals(4, userService.getAllUsersNum().intValue());
    }
}