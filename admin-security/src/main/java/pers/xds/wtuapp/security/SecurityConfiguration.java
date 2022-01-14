package pers.xds.wtuapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pers.xds.wtuapp.common.security.role.Roles;
import pers.xds.wtuapp.security.common.AuthenticationEntryPointImpl;
import pers.xds.wtuapp.security.common.CorsConfigurationSourceImpl;
import pers.xds.wtuapp.security.configuration.ExtraSecurityProperties;
import pers.xds.wtuapp.security.handler.LoginFailHandler;
import pers.xds.wtuapp.security.handler.LoginSuccessHandler;

/**
 * @author DeSen Xu
 * @date 2021-12-22 21:47
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private ExtraSecurityProperties extraSecurityProperties;

    @Autowired
    public void setExtraSecurityProperties(ExtraSecurityProperties extraSecurityProperties) {
        this.extraSecurityProperties = extraSecurityProperties;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());

        http.authorizeRequests()
                // 版本文件上传的接口
                .antMatchers("/version/**").hasRole(Roles.ADMIN.getRole())
                // App安装包下载接口
                .antMatchers("/download/**").permitAll()
                // App版本信息接口
                .antMatchers("/info/**").permitAll()
                // App所有文件下载接口
                .antMatchers("/app/**").permitAll()
                // App版本更新接口
                .antMatchers("/update/**").hasRole(Roles.ADMIN.getRole())
                // Nginx接口认证
                .antMatchers("/nginx/auth").hasRole(Roles.ADMIN.getRole());

        
        http.cors().configurationSource(new CorsConfigurationSourceImpl(extraSecurityProperties.getCorsAllowedServer()));

        http.formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(new LoginFailHandler())
                .successHandler(new LoginSuccessHandler());

        http.rememberMe().alwaysRemember(true);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
