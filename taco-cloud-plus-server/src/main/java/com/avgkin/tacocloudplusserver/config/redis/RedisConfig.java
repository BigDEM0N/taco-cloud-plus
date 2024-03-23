package com.avgkin.tacocloudplusserver.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableConfigurationProperties(RedisConfigProperties.class)
public class RedisConfig {
    @Bean
    public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext,RedisConfigProperties properties){
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec());
        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
//                .setSentinelPassword(properties.getPassword())
                .setPassword(properties.getPassword())
//                .setSlaveConnectionPoolSize(properties.getPoolSize())
//                .setSlaveConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setConnectionPoolSize(properties.getPoolSize())
                .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setIdleConnectionTimeout(properties.getIdleTimeout())
//                .setMasterConnectionPoolSize(properties.getPoolSize())
//                .setMasterConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setConnectTimeout(properties.getConnectTimeout())
                .setRetryAttempts(properties.getRetryAttempts())
                .setRetryInterval(properties.getRetryInterval())
                .setPingConnectionInterval(properties.getPingInterval())
                .setKeepAlive(properties.isKeepAlive())
                .setDatabase(0)
//                .setMasterName(properties.getMastername())
//                .setCheckSentinelsList(false)
//                .setSentinelAddresses(Arrays.stream(properties.getSentinelAddress()).toList())
        ;
        return Redisson.create(config);
    }
}
