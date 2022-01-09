package pers.xds.wtuapp.service;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import pers.xds.wtuapp.redis.bean.AppInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    AppInfo getHotUpdateVersionInfo();

    /**
     * 获取安卓新版本信息
     * @return 新版本信息
     */
    @Nullable
    AppInfo getAndroidVersionInfo();



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
     * @param minVersionCode 安装更新包所需的最小版本号
     */
    void publishHotUpdateVersion(String versionName, int versionCode, int minVersionCode);

    /**
     * 发布新版本信息, 只有发布后才能从app直接下载
     * 该方法用于第一次发布版本信息, 会直接替换相关最新版本信息
     * @param versionName 版本名称
     * @param versionCode 版本号
     */
    @Deprecated
    void publishHotUpdateVersion(String versionName, int versionCode);

    /**
     * 升级版本, 自动添加版本号
     * @param versionName 版本名称
     */
    @Deprecated
    void publishHotUpdateVersion(String versionName);


    /**
     * 发布安卓安装包的新版本, 版本号自动加一
     * @param versionName 版本名称
     */
    void publishAndroidVersion(String versionName);

    /**
     * 发布安卓安装包的新版本
     * @param versionName 版本名称
     * @param versionCode 版本号
     */
    void publishAndroidVersion(String versionName, int versionCode);

    /**
     * 获取Android安装包的文件列表
     * @return 文件列表
     */
    File[] getAndroidApkList();

    /**
     * 获取热更新资源列表
     * @return 资源列表
     */
    File[] getHotUpdateResourcesList();

    /**
     * 删除安卓的版本文件
     * @param fileName 版本名称
     * @return 是否删除成功
     * @throws FileNotFoundException 没有找到该文件
     */
    boolean deleteAndroidApk(String fileName) throws FileNotFoundException;

    /**
     * 删除热更新资源
     * @param fileName 版本名称
     * @return 是否删除成功
     * @throws FileNotFoundException 没有找到该文件
     */
    boolean deleteWgt(String fileName) throws FileNotFoundException;
}
