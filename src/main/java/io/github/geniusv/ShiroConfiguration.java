package io.github.geniusv;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 9/28/17.
 */
@Configuration
public class ShiroConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public SecurityManager securityManager(DefaultWebSessionManager defaultWebSessionManager) {
        logger.debug("ShiroConfiguration creating securityManager ");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSessionManager(defaultWebSessionManager);
        return manager;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionDAO(sessionDAO);
        manager.setDeleteInvalidSessions(true);
        manager.setGlobalSessionTimeout(180000);
        manager.setSessionValidationSchedulerEnabled(true);
        return manager;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        return filterFactoryBean;
    }
}
