package pers.xds.wtuapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                        .antMatchers("/user").hasAnyRole("t");

        http.cors().configurationSource(new CorsConfigurationSourceImpl(extraSecurityProperties.getCorsAllowedServer()));

        http.formLogin()
                .loginProcessingUrl("/login")
                .failureHandler(new LoginFailHandler())
                .successHandler(new LoginSuccessHandler());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
