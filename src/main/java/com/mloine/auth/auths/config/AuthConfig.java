package com.mloine.auth.auths.config;

import com.mloine.auth.auths.aop.LoginAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @className: AuthConfig
 * @Description:TOO
 * @Author:mloine
 * @Date：2019/6/1114:06
 **/
@SuppressWarnings("deprecation")
@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {

    private final LoginAuthInterceptor loginAuthInterceptor;

    public AuthConfig(LoginAuthInterceptor loginAuthInterceptor) {
        this.loginAuthInterceptor = loginAuthInterceptor;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}