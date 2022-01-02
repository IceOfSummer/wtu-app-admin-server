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

    public static final String HOT_UPDATE_PATH = "file:app/hotUpdate/";

    public static final String FULL_APK_PATH = "file:app/full/android/";

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
        saveFile(apk, versionName, FULL_APK_PATH);
    }

    @Override
    public void updateHotUpdateResources(MultipartFile wgt, String versionName) throws IOException {
        saveFile(wgt, versionName, HOT_UPDATE_PATH);
    }

    @Override
    public void publishHotUpdateVersion(String versionName, int versionCode) {
        appInfoRedisMapper.updateHotUpdateVersion(versionName, versionCode);
    }

    @Override
    public void publishHotUpdateVersion(String versionName) {
        appInfoRedisMapper.updateHotUpdateVersion(versionName);
    }

    @Override
    public void publishAndroidVersion(String versionName) {
        appInfoRedisMapper.updateAndroidVersion(versionName);
    }

    @Override
    public void publishAndroidVersion(String versionName, int versionCode) {
        appInfoRedisMapper.updateAndroidVersion(versionName, versionCode);
    }

    @SneakyThrows
    @Override
    public File[] getAndroidApkList() {
        return ResourceUtils.getFile(FULL_APK_PATH).listFiles();
    }

    @SneakyThrows
    @Override
    public File[] getHotUpdateResourcesList() {
        return ResourceUtils.getFile(HOT_UPDATE_PATH).listFiles();
    }

    private void saveFile(MultipartFile file , String name, String path) throws IOException {
        file.transferTo(ResourceUtils.getFile(path + name + "." + file.getName()).getAbsoluteFile());
    }

}
