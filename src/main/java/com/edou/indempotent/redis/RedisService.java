package com.edou.indempotent.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Description Redis的操作类
 * @Author 中森明菜
 * @Date 2020/4/11 12:50
 * @Version 1.0
 */
@Component
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    /*
     * @Description //写入缓存
     * @Date 2020/4/11 12:53
     * @param key
     * @param value
     *@return boolean
     **/
    public boolean set(final String key, Object value){
        boolean result = false;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * @Description //写入缓存，带过期时间
     * @Date 2020/4/11 12:57
     * @param key
     * @param value
     * @param expTime
     *@return boolean
     **/
    public boolean setExp(final String key,Object value,Long expTime){
        boolean result = false;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key,value);
            redisTemplate.expire(key,expTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * @Description //判断redis中是否存在对应key
     * @Date 2020/4/11 12:58
     * @param key
     *@return boolean
     **/
    public boolean isExist(final String key){
        return redisTemplate.hasKey(key);
    }

    /*
     * @Description //删除对应的key
     * @Date 2020/4/11 12:59
     * @param key
     *@return boolean
     **/
    public boolean delete(final String key){
        if(isExist(key)){
            return redisTemplate.delete(key);
        }
        return false;
    }

    /*
     * @Description //读取对应key的值
     * @Date 2020/4/11 13:00
     * @param key
     *@return java.lang.Object
     **/
    public Object get(final String key){
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
}
