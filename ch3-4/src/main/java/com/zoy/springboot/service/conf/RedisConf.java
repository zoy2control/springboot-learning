package com.zoy.springboot.service.conf;

import com.zoy.springboot.service.model.User4Redis;
import com.zoy.springboot.web.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by zouzp on 2019/2/13.
 */
@Configuration
public class RedisConf {

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return  stringRedisTemplate;
    }

    @Bean
    public RedisTemplate<String, User4Redis> redisRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, User4Redis> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisObjectSerializer());// ·对象序列化和反序列化

        return redisTemplate;
    }
}
