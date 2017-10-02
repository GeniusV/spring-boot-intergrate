package io.github.geniusv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 8/23/17.
 */

@Controller
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("io.github.geniusv.domain.mapper")
public class Application {

    @RequestMapping(value = "/")
    @ResponseBody
    public String helloWorld() {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
