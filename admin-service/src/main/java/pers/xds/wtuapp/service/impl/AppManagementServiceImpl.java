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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public AppInfo getNewVersionInfo() {
        return appInfoRedisMapper.getNewVersionInfo();
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
    public void publishNewVersion(String versionName, int versionCode) {
        appInfoRedisMapper.initVersionInfo(versionName, versionCode);
    }

    @Override
    public void publishNewVersion(String versionName) {
        appInfoRedisMapper.updateVersion(versionName);
    }

    @SneakyThrows
    @Override
    public String[] getAndroidApkList() {
        return ResourceUtils.getFile(FULL_APK_PATH).list();
    }

    @SneakyThrows
    @Override
    public String[] getHotUpdateResourcesList() {
        return ResourceUtils.getFile(HOT_UPDATE_PATH).list();
    }

    private void saveFile(MultipartFile file , String name, String path) throws IOException {
        file.transferTo(ResourceUtils.getFile(path + name + "." + file.getName()).getAbsoluteFile());
    }

}
