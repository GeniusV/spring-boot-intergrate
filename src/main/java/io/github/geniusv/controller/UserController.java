package io.github.geniusv.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 9/25/17.
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hello")
    public String hello() {
        Session session1 = SecurityUtils.getSubject().getSession();
        session1.setAttribute("test", "test");
        return "/hello";
    }
}
