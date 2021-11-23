package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
//                .addPathPatterns("/api/**")
                .addPathPatterns("/page/end/**")
                .excludePathPatterns("/page/end/login.html", "/page/end/register.html", "/page/end/auth.html", "/page/end/person.html");
//                .excludePathPatterns("/api/user/login", "/api/user/register");
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
