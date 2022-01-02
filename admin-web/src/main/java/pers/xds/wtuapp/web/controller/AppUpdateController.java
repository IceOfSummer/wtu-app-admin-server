package pers.xds.wtuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xds.wtuapp.common.web.ResBean;
import pers.xds.wtuapp.service.AppManagementService;

/**
 * APP升级所用接口
 * @author DeSen Xu
 * @date 2021-12-29 16:37
 */
@RestController
@RequestMapping("/app")
public class AppUpdateController {

    private AppManagementService appManagementService;

    @Autowired
    public void setAppManagementService(AppManagementService appManagementService) {
        this.appManagementService = appManagementService;
    }

    @PostMapping("/wgt/update")
    public ResBean<Void> updateVersionInfo(@RequestParam String versionName,
                                           @RequestParam(required = false) Integer versionCode) {
        if (versionCode != null) {
            appManagementService.publishHotUpdateVersion(versionName, versionCode);
        } else {
            appManagementService.publishHotUpdateVersion(versionName);
        }
        return ResBean.success();
    }

    @PostMapping("/android/update")
    public ResBean<Void> updateAndroidInfo(@RequestParam String versionName,
                                           @RequestParam(required = false) Integer versionCode) {
        if (versionCode != null) {
            appManagementService.publishAndroidVersion(versionName, versionCode);
        } else {
            appManagementService.publishAndroidVersion(versionName);
        }
        return ResBean.success();
    }


}
