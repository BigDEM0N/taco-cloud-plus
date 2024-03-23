package com.avgkin.tacocloudplusserver.utils;

import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedissonClient redissonClient;
    public <K,V> boolean putKv(String bucketName,K key,V value){
        if(key!=null&&value!=null&&bucketName!=null){
            RMapCache<K,V> map = redissonClient.getMapCache(bucketName);
            map.put(key,value,300, TimeUnit.SECONDS);
            return true;
        }else{
            throw new RuntimeException();
        }
    }
    public <K,V> V getValue(String bucketName,K key){
        if(key!=null&&bucketName!=null){
            RMapCache<K,V> map = redissonClient.getMapCache(bucketName);
            return map.get(key);
        }else{
            throw new RuntimeException();
        }
    }
    public <K,V> V remove(String bucketName,K key){
        if(key!=null&&bucketName!=null){
            RMapCache<K,V> map = redissonClient.getMapCache(bucketName);
            return map.remove(key);
        }else{
            throw new RuntimeException();
        }
    }
    public boolean tryLock(String lockKey) throws InterruptedException {
        RLock rLock = redissonClient.getLock(lockKey);
        return rLock.tryLock();
    }
}
