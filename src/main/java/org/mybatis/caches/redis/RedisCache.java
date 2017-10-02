/**
 * Copyright 2015-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.caches.redis;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Cache adapter for Redis.
 *
 * @author Eduardo Macarron
 */
public final class RedisCache implements Cache {

    private static JedisPool pool;
    private final ReadWriteLock readWriteLock = new DummyReadWriteLock();
    private String id;
    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
        RedisConfig redisConfig = RedisConfigurationBuilder.getInstance().parseConfiguration();
        pool = new JedisPool(redisConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getConnectionTimeout(), redisConfig.getPassword(),
                redisConfig.getDatabase());
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>Redis Cache created");
        logger.debug(redisConfig.getHost() + ":" + redisConfig.getPort() + "[" + redisConfig.getDatabase() + "]");

    }


    private Object execute(RedisCallback callback) {
        Jedis jedis = pool.getResource();
        try {
            return callback.doWithRedis(jedis);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        return (Integer) execute(new RedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                Map<byte[], byte[]> result = jedis.hgetAll(id.getBytes());
                return result.size();
            }
        });
    }

    @Override
    public void putObject(final Object key, final Object value) {
        execute(new RedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                jedis.hset(id.getBytes(), key.toString().getBytes(), SerializeUtil.serialize(value));
                logger.debug(">>>>cache put object key: " + key.toString().replace('\n', ' ').trim());
                return null;
            }
        });
    }

    @Override
    public Object getObject(final Object key) {

        return execute(new RedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                logger.debug(">>>>cache get object key: " + key.toString().replace('\n', ' ').trim());
                return SerializeUtil.unserialize(jedis.hget(id.getBytes(), key.toString().getBytes()));
            }
        });
    }

    @Override
    public Object removeObject(final Object key) {
        return execute(new RedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                return jedis.hdel(id, key.toString());
            }
        });
    }

    @Override
    public void clear() {
        execute(new RedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                jedis.del(id);
                return null;
            }
        });

    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public String toString() {
        return "Redis {" + id + "}";
    }

}
