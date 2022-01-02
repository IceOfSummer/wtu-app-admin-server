package pers.xds.wtuapp.redis.mapper;

import org.springframework.lang.Nullable;
import pers.xds.wtuapp.redis.bean.AppInfo;

/**
 * @author DeSen Xu
 * @date 2021-12-28 22:57
 */
public interface AppInfoRedisMapper {


    /**
     * 获取最新版本的信息
     * @return 新版本信息
     */
    @Nullable
    AppInfo getHotUpdateVersionInfo();

    /**
     * 获取安卓最新版本的信息
     * @return 新版本信息
     */
    @Nullable
    AppInfo getAndroidUpdateVersionInfo();

    /**
     * 更新版本信息, 版本号自动加一
     * @param versionName 版本名称
     */
    void updateHotUpdateVersion(String versionName);

    /**
     * 初始化热更新资源版本信息
     * @param versionName 版本名称
     * @param versionCode 版本号
     */
    void updateHotUpdateVersion(String versionName, int versionCode);

    /**
     * 更新安卓的相关版本信息
     * @param versionName 版本名称
     */
    void updateAndroidVersion(String versionName);

    /**
     * 更新安卓的相关版本信息
     * @param versionName 版本名称
     * @param versionCode 版本号
     */
    void updateAndroidVersion(String versionName, int versionCode);

}
