package pers.xds.wtuapp.redis.mapper;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author DeSen Xu
 * @date 2021-12-28 22:58
 */
public abstract class RedisBaseMapper {

    protected final RedisTemplate<String, String> redisTemplate;

    public RedisBaseMapper(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
