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

    public static final String FILE_SIZE_KEY = "file-size";

    /**
     * 升级当前版本
     * KEYS[1] hash的键
     * KEYS[2] versionName的键
     * KEYS[3] versionCode的键
     * KEYS[4] file-size
     * ARGV[1] versionName的值
     * ARGV[2] file-size
     */
    private static final RedisScript<Void> LUA_UPDATE_VERSION = RedisScript.of("redis.call(\"hmset\", KEYS[1], KEYS[2], ARGV[1], KEYS[4], ARGV[2])\n" +
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
        Object fileSizeObj = entries.get(FILE_SIZE_KEY);
        if (fileSizeObj == null) {
            return null;
        }
        int fileSize = Integer.parseInt(fileSizeObj.toString());
        return new AppInfo(versionName, versionCode, fileSize);
    }


    @Override
    public void updateHotUpdateVersion(String versionName, long size){
        redisTemplate.execute(LUA_UPDATE_VERSION,
                Arrays.asList(WGT_KEY, WGT_VERSION_NAME_KEY, WGT_VERSION_CODE_KEY, FILE_SIZE_KEY), versionName, size);
    }

    @Override
    public void updateHotUpdateVersion(String versionName, int versionCode, long size) {
        updateVersion(WGT_VERSION_NAME_KEY, versionName, WGT_VERSION_CODE_KEY, versionCode, size, WGT_KEY);
    }

    @Override
    public void updateAndroidVersion(String versionName, long size) {
        redisTemplate.execute(LUA_UPDATE_VERSION,
                Arrays.asList(ANDROID_KEY, ANDROID_VERSION_NAME_KEY, ANDROID_VERSION_CODE_KEY, FILE_SIZE_KEY), versionName, size);
    }

    @Override
    public void updateAndroidVersion(String versionName, int versionCode, long size) {
        updateVersion(ANDROID_VERSION_NAME_KEY, versionName, ANDROID_VERSION_CODE_KEY, versionCode, size, ANDROID_KEY);
    }

    private void updateVersion(String versionNameKey, String versionNameValue,
                               String versionCodeKey, int versionCodeValue,
                               long size, String key) {
        HashMap<String, String> map = new HashMap<>(3);
        map.put(versionNameKey, versionNameValue);
        map.put(versionCodeKey, String.valueOf(versionCodeValue));
        map.put(FILE_SIZE_KEY, String.valueOf(size));
        redisTemplate.opsForHash().putAll(key, map);
    }


}
