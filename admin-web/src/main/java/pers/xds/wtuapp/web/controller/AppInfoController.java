package pers.xds.wtuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xds.wtuapp.common.web.ResBean;
import pers.xds.wtuapp.common.web.ResBeanCode;
import pers.xds.wtuapp.redis.bean.AppInfo;
import pers.xds.wtuapp.service.AppManagementService;

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

    @GetMapping("/android")
    public ResBean<String[]> getAppAndroidApkList() {
        return ResBean.success(appManagementService.getAndroidApkList());
    }

    @GetMapping("/wgt")
    public ResBean<String[]> getAppHotUpdateResourcesList() {
        return ResBean.success(appManagementService.getHotUpdateResourcesList());
    }

    @GetMapping("/")
    public ResBean<AppInfo> getNewVersionInfo() {
        AppInfo newVersionInfo = appManagementService.getNewVersionInfo();
        if (newVersionInfo == null) {
            return ResBean.success();
        }
        return ResBean.success(newVersionInfo);
    }

}
