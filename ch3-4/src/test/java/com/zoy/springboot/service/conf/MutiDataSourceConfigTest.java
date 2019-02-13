package com.zoy.springboot.service.conf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zouzp on 2019/2/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MutiDataSourceConfigTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    @Before
    public void setUp() {
        // ·清空不同数据源的 user_info表
        primaryJdbcTemplate.update("DELETE FROM user_info");
        secondJdbcTemplate.update("DELETE FROM user_info");
    }

    @Test
    public void test() {
        // 往第一个数据源中插入两条数据
        primaryJdbcTemplate.update("insert into user_info(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        primaryJdbcTemplate.update("insert into user_info(id,name,age) values(?, ?, ?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        secondJdbcTemplate.update("insert into user_info(id,name,age) values(?, ?, ?)", 1, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", primaryJdbcTemplate.queryForObject("select count(1) from user_info", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", secondJdbcTemplate.queryForObject("select count(1) from user_info", String.class));
    }
}