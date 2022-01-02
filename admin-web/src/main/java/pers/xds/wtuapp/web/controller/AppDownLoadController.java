package pers.xds.wtuapp.web.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xds.wtuapp.redis.bean.AppInfo;
import pers.xds.wtuapp.service.AppManagementService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author DeSen Xu
 * @date 2022-01-02 19:51
 */
@RestController
@RequestMapping("/download")
public class AppDownLoadController {

    private AppManagementService appManagementService;

    @Autowired
    public void setAppManagementService(AppManagementService appManagementService) {
        this.appManagementService = appManagementService;
    }

    @GetMapping("/android")
    public void downloadNewVersionAndroidApk(HttpServletResponse response) {
        AppInfo androidVersionInfo = appManagementService.getAndroidVersionInfo();
        if (androidVersionInfo == null) {
            return;
        }
        String versionName = androidVersionInfo.getVersionName();
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader(HttpHeaders.LOCATION, "/app/full/android/" + versionName);

    }
}
