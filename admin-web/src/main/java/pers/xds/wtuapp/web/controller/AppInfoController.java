package pers.xds.wtuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xds.wtuapp.common.web.ResBean;
import pers.xds.wtuapp.redis.bean.AppInfo;
import pers.xds.wtuapp.service.AppManagementService;
import pers.xds.wtuapp.web.bean.FileInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理界面获取APP版本信息所用接口
 * @author DeSen Xu
 * @date 2021-12-28 22:56
 */
@RestController
@RequestMapping("/info")
public class AppInfoController {

    private AppManagementService appManagementService;

    @Autowired
    public void setAppManagementService(AppManagementService appManagementService) {
        this.appManagementService = appManagementService;
    }

    @GetMapping("/android/list")
    public ResBean<List<FileInfo>> getAppAndroidApkList() {
        List<FileInfo> collect = Arrays.stream(appManagementService.getAndroidApkList()).map(FileInfo::new).collect(Collectors.toList());
        return ResBean.success(collect);
    }

    @GetMapping("/wgt/list")
    public ResBean<List<FileInfo>> getAppHotUpdateResourcesList() {
        List<FileInfo> collect = Arrays.stream(appManagementService.getHotUpdateResourcesList()).map(FileInfo::new).collect(Collectors.toList());
        return ResBean.success(collect);
    }

    @GetMapping("/wgt")
    public ResBean<AppInfo> getWgtNewVersionInfo() {
        AppInfo newVersionInfo = appManagementService.getHotUpdateVersionInfo();
        if (newVersionInfo == null) {
            return ResBean.success();
        }
        return ResBean.success(newVersionInfo);
    }

    @GetMapping("/android")
    public ResBean<AppInfo> getAndroidVersionInfo() {
        AppInfo androidVersionInfo = appManagementService.getAndroidVersionInfo();
        if (androidVersionInfo == null) {
            return ResBean.success();
        }
        return ResBean.success(androidVersionInfo);
    }

}
