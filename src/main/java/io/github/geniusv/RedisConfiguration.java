package io.github.geniusv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 9/26/17.
 */

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
    private final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    @Bean(name = "sessionRedis")
    public RedisTemplate redisTemplate(
            @Value("${redis.session.hostname}") String hostname,
            @Value("${redis.session.port}") int port,
            @Value("${redis.session.password}") String password,
            @Value("${redis.session.index}") int index,
            @Value("${redis.session.maxIdle}") int maxIdle,
            @Value("${redis.session.maxTotal}") int maxTotal,
            @Value("${redis.session.maxWaitMillis}") long maxWaitMillis,
            @Value("${redis.session.testOnBorrow}") boolean testOnBorrow
    ) {
        RedisTemplate redisTemplate = new RedisTemplate();
//
//        // set serializer to convert objects.
//        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////          test
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        serializer.setObjectMapper(objectMapper);
//
//        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory(hostname, port, password, index, maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        return redisTemplate;
    }

    public RedisConnectionFactory redisConnectionFactory(String hostname, int port, String password, int index,
                                                         int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostname);
        jedisConnectionFactory.setPort(port);
        if (!StringUtils.isEmpty(password)) {
            jedisConnectionFactory.setPassword(password);
        }
        if (index != 0) {
            jedisConnectionFactory.setDatabase(index);
        }
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));

        jedisConnectionFactory.afterPropertiesSet();

        return jedisConnectionFactory;
    }

    public JedisPoolConfig jedisPoolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }
}
