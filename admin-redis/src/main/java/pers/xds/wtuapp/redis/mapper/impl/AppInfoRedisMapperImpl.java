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

    public static final String WGT_KEY = "wgt";

    public static final String WGT_VERSION_CODE_KEY = "wgt-versionCode";

    public static final String WGT_VERSION_NAME_KEY = "wgt-versionName";

    public static final String ANDROID_KEY = "android";

    public static final String ANDROID_VERSION_CODE_KEY = "android-versionCode";

    public static final String ANDROID_VERSION_NAME_KEY = "android-versionName";

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
    public AppInfo getHotUpdateVersionInfo() {
        return getVersionInfo(WGT_KEY, WGT_VERSION_NAME_KEY, WGT_VERSION_CODE_KEY);
    }

    @Override
    public AppInfo getAndroidUpdateVersionInfo() {
        return getVersionInfo(ANDROID_KEY, ANDROID_VERSION_NAME_KEY, ANDROID_VERSION_CODE_KEY);
    }

    private AppInfo getVersionInfo(String key, String versionNameKey, String versionCodeKey) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        Object versionCodeObj = entries.get(versionCodeKey);
        if (versionCodeObj == null) {
            return null;
        }
        int versionCode = Integer.parseInt(versionCodeObj.toString());
        Object versionNameObj = entries.get(versionNameKey);
        if (versionNameObj == null) {
            return null;
        }
        String versionName = versionNameObj.toString();
        return new AppInfo(versionName, versionCode);
    }


    @Override
    public void updateHotUpdateVersion(String versionName){
        redisTemplate.execute(LUA_UPDATE_VERSION, Arrays.asList(WGT_KEY, WGT_VERSION_NAME_KEY, WGT_VERSION_CODE_KEY), versionName);
    }

    @Override
    public void updateHotUpdateVersion(String versionName, int versionCode) {
        HashMap<String, String> map = new HashMap<>(2);
        map.put(WGT_VERSION_NAME_KEY, versionName);
        map.put(WGT_VERSION_CODE_KEY, String.valueOf(versionCode));
        redisTemplate.opsForHash().putAll(WGT_KEY, map);
    }

    @Override
    public void updateAndroidVersion(String versionName) {
        redisTemplate.execute(LUA_UPDATE_VERSION, Arrays.asList(ANDROID_KEY, ANDROID_VERSION_NAME_KEY, ANDROID_VERSION_CODE_KEY), versionName);
    }

    @Override
    public void updateAndroidVersion(String versionName, int versionCode) {
        HashMap<String, String> map = new HashMap<>(2);
        map.put(ANDROID_VERSION_NAME_KEY, versionName);
        map.put(ANDROID_VERSION_CODE_KEY, String.valueOf(versionCode));
        redisTemplate.opsForHash().putAll(ANDROID_KEY, map);
    }


}
