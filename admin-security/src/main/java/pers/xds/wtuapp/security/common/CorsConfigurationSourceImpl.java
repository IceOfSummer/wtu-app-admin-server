package pers.xds.wtuapp.security.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author DeSen Xu
 * @date 2021-12-22 22:44
 */
public class CorsConfigurationSourceImpl implements CorsConfigurationSource {

    private final List<String> allowedServerList;

    private static final List<String> ALLOWED_METHOD = Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.OPTIONS.name());

    private static final List<String> ALLOWED_HEADERS = Arrays.asList("Origin", "Content-Type", "Accept");

    public CorsConfigurationSourceImpl(List<String> allowedServerList) {
        this.allowedServerList = allowedServerList;
    }

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        if (allowedServerList.contains(origin)) {
            corsConfiguration.setAllowedMethods(ALLOWED_METHOD);
            corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
            corsConfiguration.addAllowedOrigin(origin);
            corsConfiguration.setAllowCredentials(true);
            // 上面这4个必须都要写，不然仍然会被跨域拦截, 若不写, 可以使用下面的方法补全
//            corsConfiguration.applyPermitDefaultValues();
            corsConfiguration.setMaxAge(1800L);
        }
        return corsConfiguration;
    }
}
