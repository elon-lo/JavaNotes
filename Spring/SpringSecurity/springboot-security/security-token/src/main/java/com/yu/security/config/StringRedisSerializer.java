package com.yu.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * redis配置string序列化(key序列化)
 *
 * @author elonlo
 * @date 2023/7/8 13:52
 */
public class StringRedisSerializer<T> implements RedisSerializer<Object> {

    private final Charset charset;

    public StringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public StringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        final String target = "\"";
        final String replacement = "";

        String string = JSON.toJSONString(object);
        if (StringUtils.hasText(string)) {
            return null;
        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (null == bytes ? null : new String(bytes, charset));
    }
}
