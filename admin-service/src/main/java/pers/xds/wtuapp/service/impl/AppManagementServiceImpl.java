package pers.xds.wtuapp.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import pers.xds.wtuapp.redis.bean.AppInfo;
import pers.xds.wtuapp.redis.mapper.AppInfoRedisMapper;
import pers.xds.wtuapp.service.AppManagementService;

import java.io.*;

/**
 * @author DeSen Xu
 * @date 2021-12-28 23:14
 */
@Service
public class AppManagementServiceImpl implements AppManagementService {

    private AppInfoRedisMapper appInfoRedisMapper;

    public static final String WGT_RESOURCE_PREFIX = "file:app/hotUpdate/";

    public static final String ANDROID_APK_PREFIX = "file:app/full/android/";

    @Autowired
    public void setAppInfoRedisMapper(AppInfoRedisMapper appInfoRedisMapper) {
        this.appInfoRedisMapper = appInfoRedisMapper;
    }

    @Override
    public AppInfo getHotUpdateVersionInfo() {
        return appInfoRedisMapper.getHotUpdateVersionInfo();
    }

    @Override
    public AppInfo getAndroidVersionInfo() {
        return appInfoRedisMapper.getAndroidUpdateVersionInfo();
    }

    @Override
    public void updateAndroidApp(MultipartFile apk, String versionName) throws IOException {
        saveFile(apk, versionName, ANDROID_APK_PREFIX);
    }

    @Override
    public void updateHotUpdateResources(MultipartFile wgt, String versionName) throws IOException {
        saveFile(wgt, versionName, WGT_RESOURCE_PREFIX);
    }

    @Override
    public void publishHotUpdateVersion(String versionName, int versionCode) {
        appInfoRedisMapper.updateHotUpdateVersion(versionName, versionCode, getFileSize(versionName, WGT_RESOURCE_PREFIX));
    }

    @Override
    public void publishHotUpdateVersion(String versionName) {
        appInfoRedisMapper.updateHotUpdateVersion(versionName, getFileSize(versionName, WGT_RESOURCE_PREFIX));
    }

    @Override
    public void publishAndroidVersion(String versionName) {
        appInfoRedisMapper.updateAndroidVersion(versionName, getFileSize(versionName, ANDROID_APK_PREFIX));
    }

    @Override
    public void publishAndroidVersion(String versionName, int versionCode) {
        appInfoRedisMapper.updateAndroidVersion(versionName, versionCode, getFileSize(versionName, ANDROID_APK_PREFIX));
    }

    /**
     * 获取文件大小 (MB)
     * @param versionName 文件名称
     * @param pathPrefix 文件前缀路径
     * @return 文件大小(KB)
     */
    @SneakyThrows
    private long getFileSize(String versionName, String pathPrefix) {
        return ResourceUtils.getFile(pathPrefix + versionName).length() / 1024;
    }

    @SneakyThrows
    @Override
    public File[] getAndroidApkList() {
        return ResourceUtils.getFile(ANDROID_APK_PREFIX).listFiles();
    }

    @SneakyThrows
    @Override
    public File[] getHotUpdateResourcesList() {
        return ResourceUtils.getFile(WGT_RESOURCE_PREFIX).listFiles();
    }

    @Override
    public boolean deleteAndroidApk(String fileName) throws FileNotFoundException {
        File file = ResourceUtils.getFile(ANDROID_APK_PREFIX + fileName);
        return file.delete();
    }

    @Override
    public boolean deleteWgt(String fileName) throws FileNotFoundException {
        File file = ResourceUtils.getFile(WGT_RESOURCE_PREFIX + fileName);
        return file.delete();
    }

    private void saveFile(MultipartFile file , String name, String path) throws IOException {
        file.transferTo(ResourceUtils.getFile(path + name + "." + file.getName()).getAbsoluteFile());
    }


}
