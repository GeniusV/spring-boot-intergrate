package io.github.geniusv.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 9/28/17.
 */
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "unchecked"})
@Service
public class RedisSessionDao extends AbstractSessionDAO{
    private long expiredTime = 1800000;
    private static final Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);

    @Autowired
    @Qualifier("sessionRedis")
    private RedisTemplate redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        logger.debug("doCreate: ["+session.getId()+"]");
        Serializable sessionId = this.getSessionIdGenerator().generateId(session);
        this.assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(session.getId(), session, expiredTime, TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        if (serializable == null) {
            logger.warn("doReadSession get a null sessionId");
            return null;
        }
        Session session = (Session) redisTemplate.opsForValue().get(serializable);
        if (session == null) {
            logger.debug("doReadSession: get session null");
        } else {
            logger.debug("doReadSession: ["+ session.getId() + "]");
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session == null) {
            logger.warn("update get a null session");
            return;
        } else if (session.getId() == null) {
            logger.warn("update get a session with a null sessionId");
            return;
        }
        session.setTimeout(expiredTime);
        redisTemplate.opsForValue().set(session.getId(), session, expiredTime, TimeUnit.MILLISECONDS);
        logger.debug("update session: [" + session.getId() + "]");
    }

    @Override
    public void delete(Session session) {
        if (session == null) {
            logger.warn("delete get a null session");
            return;
        } else if (session.getId() == null) {
            logger.warn("delete get a session with a null sessionId");
            return;
        }

        redisTemplate.opsForValue().getOperations().delete(session.getId());
        logger.debug("delete session: ]" + session.getId() + "]");
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return (Collection<Session>) redisTemplate.keys("*");
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Logger getLogger() {
        return logger;
    }


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
