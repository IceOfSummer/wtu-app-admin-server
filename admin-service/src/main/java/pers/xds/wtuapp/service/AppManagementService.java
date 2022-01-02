package pers.xds.wtuapp.service;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pers.xds.wtuapp.redis.bean.AppInfo;
import java.io.IOException;
import java.util.List;

/**
 * @author DeSen Xu
 * @date 2021-12-28 23:13
 */
public interface AppManagementService {

    /**
     * 获取最新版本的版本信息
     * @return 版本信息
     */
    @Nullable
    AppInfo getNewVersionInfo();



    /**
     * 上传Android更新包
     * @param apk 安装包文件
     * @param versionName 版本名称
     * @throws IOException 文件保存失败
     */
    void updateAndroidApp(MultipartFile apk,  String versionName) throws IOException;

    /**
     * 上传热更新资源
     * @param wgt 安装包文件
     * @param versionName 版本名称
     * @throws IOException 文件保存失败
     */
    void updateHotUpdateResources(MultipartFile wgt, String versionName) throws IOException;

    /**
     * 发布新版本信息, 只有发布后才能从app直接下载
     * 该方法用于第一次发布版本信息, 会直接替换相关最新版本信息
     * @param versionName 版本名称
     * @param versionCode 版本号
     */
    void publishNewVersion(String versionName, int versionCode);

    /**
     * 升级版本, 自动添加版本号
     * @param versionName 版本名称
     */
    void publishNewVersion(String versionName);

    /**
     * 获取Android安装包的文件列表
     * @return 文件列表
     */
    String[] getAndroidApkList();

    /**
     * 获取热更新资源列表
     * @return 资源列表
     */
    String[] getHotUpdateResourcesList();
}
