package io.github.geniusv.domain.mapper;

import io.github.geniusv.domain.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 9/26/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    UserMapper userMapper;

    @Test
    public void testMybatisConnection() {
        User user = userMapper.selectByPrimaryKey(1L);
        logger.info(user.getUserName());
        Assert.assertEquals("admin", user.getUserName());
    }


    public static Logger getLogger() {
        return logger;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}