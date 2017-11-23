package io.github.geniusv.service;

import io.github.geniusv.domain.mapper.UserMapper;
import io.github.geniusv.domain.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 11/20/17.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Long id) {
        SecurityUtils.getSubject();
        return new User();
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
