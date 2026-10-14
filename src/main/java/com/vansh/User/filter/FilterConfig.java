package com.vansh.User.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilterBean() {
        FilterRegistrationBean<LoggingFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new LoggingFilter());
        filterRegistration.addUrlPatterns("/api/user", "/api/user/*", "/api/users");
        filterRegistration.setOrder(2);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterBean() {
        FilterRegistrationBean<AuthenticationFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new AuthenticationFilter());
        filterRegistration.addUrlPatterns("/api/user", "/api/user/*", "/api/users");
        filterRegistration.setOrder(1);
        return filterRegistration;
    }
}
