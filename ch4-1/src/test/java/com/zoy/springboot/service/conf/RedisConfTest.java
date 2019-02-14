package com.zoy.springboot.service.conf;

import com.zoy.springboot.service.model.User4Redis;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zouzp on 2019/2/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisConfTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, User4Redis> redisTemplate;


    /**
     * ·直接测试 StringRedisTemplate，相当于 RedisTemplate<String, String>
     */
    @Test
    public void test01() {
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
    }

    /**
     * ·redis存储的 value还可以是 对象。即 RedisTemplate<String, Object>，但 Springboot不支持直接使用，
     * ·所以需要将 对象序列化和反序列化
     */
    @Test
    public void test02() {
        // 保存对象
        User4Redis user = new User4Redis("超人", 20);
        redisTemplate.opsForValue().set(user.getUserName(), user);

        user = new User4Redis("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getUserName(), user);

        user = new User4Redis("绿巨人", 40);
        redisTemplate.opsForValue().set(user.getUserName(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("绿巨人").getAge().longValue());
    }
}
