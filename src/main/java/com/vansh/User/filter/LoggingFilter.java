package com.vansh.User.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        long startTime = System.currentTimeMillis();
        logger.info("Incoming Request : method={}, path={}, ip={}", httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getRemoteAddr());
        System.out.println("LOGGING FILTER IS EXECUTING!");
        try {
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Request Completed : method={}, path={}, status={}, duration={}", httpRequest.getMethod(), httpRequest.getRequestURI(), httpResponse.getStatus(), duration);
        }
    }
}
