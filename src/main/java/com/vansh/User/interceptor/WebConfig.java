package com.vansh.User.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthorizationInteceptor authorizationInteceptor;
    private final ExecutionTimeInterceptor executionTimeInterceptor;

    public WebConfig(AuthorizationInteceptor authorizationInterceptor, ExecutionTimeInterceptor executionTimeInterceptor) {
        this.authorizationInteceptor = authorizationInterceptor;
        this.executionTimeInterceptor = executionTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInteceptor).addPathPatterns("/api/user", "/api/user/soft-delete").order(2);
        registry.addInterceptor(executionTimeInterceptor).addPathPatterns("/api/user/*", "/api/user", "/api/users").order(1);
    }
}
