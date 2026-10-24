package com.vansh.User.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ExecutionTimeInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(ExecutionTimeInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long currTime = System.currentTimeMillis();
        request.setAttribute("currTime", currTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long duration = System.currentTimeMillis() - (long) request.getAttribute("currTime");
        logger.info("Time Elapsed : " + duration);
    }
}
