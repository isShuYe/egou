package com.ego.redis.jedisdao;

import java.util.Map;

public interface JedisClusterDao {
    String set(String key,String value);
    String get(String key);
    boolean exists(String key);

}
