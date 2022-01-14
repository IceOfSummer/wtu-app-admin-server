package pers.xds.wtuapp.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DeSen Xu
 * @date 2022-01-14 10:03
 */
@RestController
@RequestMapping("/nginx")
public class NginxAuthController {

    /**
     * 这里直接用Spring Security认证, 能走过来就代表认证成功
     */
    @GetMapping("/auth")
    public void auth(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
