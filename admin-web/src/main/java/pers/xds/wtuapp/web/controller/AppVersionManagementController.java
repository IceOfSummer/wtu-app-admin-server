package pers.xds.wtuapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.xds.wtuapp.common.web.ResBean;
import pers.xds.wtuapp.common.web.ResBeanCode;
import pers.xds.wtuapp.common.web.util.StringUtils;
import pers.xds.wtuapp.service.AppManagementService;
import java.io.IOException;

/**
 * 用于管理APP版本的web接口
 * @author DeSen Xu
 * @date 2021-12-28 23:29
 */
@RestController
@RequestMapping("/version")
public class AppVersionManagementController {

    private AppManagementService appManagementService;
            
    @Autowired
    public void setAppManagementService(AppManagementService appManagementService) {
        this.appManagementService = appManagementService;
    }

    @PostMapping("/update/android")
    public ResBean<Void> uploadNewVersion(@RequestParam MultipartFile apk,
                                          @RequestParam String versionName) {
        if (StringUtils.isEmpty(versionName)) {
            return ResBean.fail(ResBeanCode.BAD_REQUEST);
        }
        try {
            appManagementService.updateAndroidApp(apk, versionName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResBean.success();
    }

    @PostMapping("/update/hot")
    public ResBean<Void> uploadHotUpdateResources(@RequestParam MultipartFile wgt,
                                                  @RequestParam String versionName) {
        if (StringUtils.isEmpty(versionName)) {
            return ResBean.fail(ResBeanCode.BAD_REQUEST);
        }
        try {
            appManagementService.updateHotUpdateResources(wgt, versionName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResBean.success();
    }
}
