package pers.xds.wtuapp.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import pers.xds.wtuapp.common.web.ResBean;
import pers.xds.wtuapp.common.web.ResBeanCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeSen Xu
 * @date 2021-12-22 22:16
 */
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        System.out.println(exception.getMessage());
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(ResBean.fail(ResBeanCode.LOGIN_FAILED)));
    }
}
