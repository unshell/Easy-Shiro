package com.unshell.easyshiro.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis服务
 */
@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key != null ? redisTemplate.opsForValue().get(key) : null;
    }

    /**
     * 放入缓存
     *
     * @param key   键
     * @param value 值
     * @return Boolean
     */
    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time<=0 为无期限
     * @return Boolean
     */
    public Boolean set(String key, Object value, Long time) {
        try {
            if (time > 0)
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            else
                set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键
     * @param item 项
     * @return
     */
    public Boolean hasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
}