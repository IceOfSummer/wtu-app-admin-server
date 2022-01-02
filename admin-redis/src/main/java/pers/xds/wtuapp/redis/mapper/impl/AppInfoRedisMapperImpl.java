package pers.xds.wtuapp.redis.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import pers.xds.wtuapp.redis.bean.AppInfo;
import pers.xds.wtuapp.redis.mapper.AppInfoRedisMapper;
import pers.xds.wtuapp.redis.mapper.RedisBaseMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DeSen Xu
 * @date 2021-12-28 23:02
 */
@Component
public class AppInfoRedisMapperImpl extends RedisBaseMapper implements AppInfoRedisMapper {

    public static final String NEW_VERSION_KEY = "newVersion";

    public static final String VERSION_CODE_KEY = "versionCode";

    public static final String VERSION_NAME_KEY = "versionName";

    /**
     * 升级当前版本
     * KEYS[1] hash的键
     * KEYS[2] versionName的键
     * KEYS[3] versionCode的键
     * ARGV[1] versionName的值
     */
    private static final RedisScript<Void> LUA_UPDATE_VERSION = RedisScript.of("redis.call(\"hset\", KEYS[1], KEYS[2], ARGV[1])\n" +
            "redis.call(\"hincrby\", KEYS[1], KEYS[3], 1)", Void.class);

    @Autowired
    public AppInfoRedisMapperImpl(RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public AppInfo getNewVersionInfo() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(NEW_VERSION_KEY);
        Object versionCodeObj = entries.get(VERSION_CODE_KEY);
        if (versionCodeObj == null) {
            return null;
        }
        int versionCode = Integer.parseInt(versionCodeObj.toString());
        Object versionNameObj = entries.get(VERSION_NAME_KEY);
        if (versionNameObj == null) {
            return null;
        }
        String versionName = versionNameObj.toString();
        return new AppInfo(versionName, versionCode);
    }

    @Override
    public void updateVersion(String versionName){
        redisTemplate.execute(LUA_UPDATE_VERSION, Arrays.asList(NEW_VERSION_KEY, VERSION_NAME_KEY, VERSION_CODE_KEY), versionName);
    }

    @Override
    public void initVersionInfo(String versionName, int versionCode) {
        HashMap<String, String> map = new HashMap<>(2);
        map.put(VERSION_NAME_KEY, versionName);
        map.put(VERSION_CODE_KEY, String.valueOf(versionCode));
        redisTemplate.opsForHash().putAll(NEW_VERSION_KEY, map);
    }


}
