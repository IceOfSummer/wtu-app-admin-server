package pers.xds.wtuapp.security.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

/**
 * @author DeSen Xu
 * @date 2021-12-22 22:33
 */
@ConfigurationProperties(prefix = "web")
@Data
public class ExtraSecurityProperties {

    /**
     * 允许跨域访问的服务器列表
     */
    private List<String> corsAllowedServer;
}
