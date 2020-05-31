package com.ego.redis.jedisdao.impl;

import com.ego.redis.jedisdao.JedisClusterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

@Repository
public class JedisClusterDaoImpl implements JedisClusterDao {
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(String key, String value) {
        jedisCluster.expire(key,1800);
        return jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public boolean exists(String key) {
        return jedisCluster.exists(key);
    }


}
