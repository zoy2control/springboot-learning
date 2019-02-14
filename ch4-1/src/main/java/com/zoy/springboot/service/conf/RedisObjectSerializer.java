package com.zoy.springboot.service.conf;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * ·Redis存储的可以是对象，比如 <String, Object>，因为Springboot不支持直接使用
 * ·我们需要将 对象序列化和 反序列化
 * ·Created by zouzp on 2019/2/13.
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];

    /**
     * ·序列化
     * @param o
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (null == o) {
            return EMPTY_ARRAY;
        }
        return serializer.convert(o);
    }

    /**
     * ·反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0) {
            return null;
        }
        return deserializer.convert(bytes);
    }
}
