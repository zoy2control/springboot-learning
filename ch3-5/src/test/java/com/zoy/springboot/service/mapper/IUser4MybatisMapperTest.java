package com.zoy.springboot.service.mapper;

import com.zoy.springboot.service.model.User4Mybatis;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zouzp on 2019/2/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IUser4MybatisMapperTest {
    @Resource
    private IUser4MybatisMapper user4MybatisMapper;

    @Test
    @Rollback // ·测试结束回滚数据，保证测试单元每次运行的数据环境独立
    public void test() throws Exception{
        // ·检验数据库是否已存在数据
        User4Mybatis result = user4MybatisMapper.findByName("aaa");
        if (null == result) {
            // ·不存在则插入
            user4MybatisMapper.insert("aaa",18);
        } else {
            // ·存在则直接判断数据值
            Assert.assertEquals(18, result.getAge().intValue());
        }
        Assert.assertEquals(18, result.getAge().intValue());
    }

    @Test
    @Rollback // ·测试结束回滚数据，保证测试单元每次运行的数据环境独立
    public void testByUser() throws Exception{
        // ·检验数据库是否已存在数据
        User4Mybatis result = user4MybatisMapper.findByName("ccc");
        if (null == result) {
            User4Mybatis user = new User4Mybatis("ccc", 18);
            // ·不存在则插入
            user4MybatisMapper.insertByUser(user);
        } else {
            // ·存在则直接判断数据值
            Assert.assertEquals(18, result.getAge().intValue());
        }
        result = user4MybatisMapper.findByName("ccc");
        Assert.assertEquals(18, result.getAge().intValue());
    }
}