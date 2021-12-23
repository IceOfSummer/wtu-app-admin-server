package pers.xds.wtuapp.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import pers.xds.wtuapp.common.web.ResBean;
import pers.xds.wtuapp.common.web.ResBeanCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeSen Xu
 * @date 2021-12-22 22:04
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(ResBean.fail(ResBeanCode.AUTHORIZATION_NEEDED)));
    }
}
