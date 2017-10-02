package io.github.geniusv.redis;

import io.github.geniusv.domain.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 9/26/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedisTest {

    @Resource(name = "sessionRedis")
    RedisTemplate redisTemplate;

    @Test
    public void redisConfigurationTest() {
        User user = new User();
        user.setUserName("test");
        user.setId(1L);
        user.setPassword("password");
        user.setStatus(1L);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("test", user);

        User value = operations.get("test");
        Assert.assertEquals("test", value.getUserName());
    }
}
