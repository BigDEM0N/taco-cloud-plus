package com.avgkin.tacocloudplusserver.config.cache;

import com.avgkin.tacocloudplusserver.config.redis.RedisConfigProperties;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@EnableConfigurationProperties(RedisConfigProperties.class)
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient,RedisConfigProperties properties) {
        org.redisson.spring.cache.CacheConfig config = new org.redisson.spring.cache.CacheConfig(properties.getTtl(),properties.getIdleTimeout());
        return new RedissonSpringCacheManager(redissonClient, (Map<String, ? extends org.redisson.spring.cache.CacheConfig>) config);
    }
}