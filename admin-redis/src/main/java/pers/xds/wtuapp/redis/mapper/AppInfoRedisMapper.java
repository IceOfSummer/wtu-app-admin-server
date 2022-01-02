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
    AppInfo getNewVersionInfo();

    /**
     * 更新版本信息, 版本号自动加一
     * @param versionName 版本名称
     */
    void updateVersion(String versionName);

    /**
     * 初始化版本信息
     * @param versionName 版本名称
     * @param versionCode 版本号
     */
    void initVersionInfo(String versionName, int versionCode);


}
