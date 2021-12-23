package pers.xds.wtuapp.security.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author DeSen Xu
 * @date 2021-12-22 22:44
 */
public class CorsConfigurationSourceImpl implements CorsConfigurationSource {

    private final List<String> allowedServerList;

    private static final List<String> ALLOWED_METHOD = Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name());

    public CorsConfigurationSourceImpl(List<String> allowedServerList) {
        this.allowedServerList = allowedServerList;
    }

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        corsConfiguration.setAllowedMethods(ALLOWED_METHOD);
        if (allowedServerList.contains(origin)) {
            corsConfiguration.addAllowedOrigin(origin);
            corsConfiguration.setAllowCredentials(true);
        }
        return corsConfiguration;
    }
}
