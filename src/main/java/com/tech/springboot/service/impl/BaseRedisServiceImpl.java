//package com.tech.springboot.service.impl;
//
//import com.tech.springboot.service.BaseRedisService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//@Service
//@RequiredArgsConstructor
//public class BaseRedisServiceImpl implements BaseRedisService {
//
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final HashOperations<String, String, Object> hashOperations;
//
//    @Override
//    public void set(String key, Object value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    @Override
//    public void setTimeToLive(String key, Long timeoutInDays) {
//        redisTemplate.expire(key, timeoutInDays, TimeUnit.DAYS);
//    }
//
//    @Override
//    public void hashSet(String key, String field, Object value) {
//        hashOperations.put(key, field, value);
//    }
//
//    @Override
//    public boolean hashExists(String key, String field) {
//        return hashOperations.hasKey(key, field);
//    }
//
//    @Override
//    public Object get(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    @Override
//    public Map<String, Object> getField(String key) {
//        return hashOperations.entries(key);
//    }
//
//    @Override
//    public Object hashGet(String key, String field) {
//        return hashOperations.get(key, field);
//    }
//
//    @Override
//    public Set<String> getFieldPrefixes(String key) {
//        return hashOperations.entries(key).keySet();
//    }
//
//    @Override
//    public void delete(String key) {
//        redisTemplate.delete(key);
//    }
//
//    @Override
//    public void delete(String key, String field) {
//        hashOperations.delete(key, field);
//    }
//
//    @Override
//    public void delete(String key, List<String> fields) {
//        for (String field : fields) {
//            hashOperations.delete(key, field);
//        }
//    }
//}
