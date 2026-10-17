package com.vansh.User.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthorizationInteceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("AuthorizationInterceptor");
        String role = request.getHeader("role");

        if (("DELETE".equals(request.getMethod()) || "PATCH".equals(request.getMethod()))
                && !"ADMIN".equals(role)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("""
                    {
                        "error" : 403,
                        "message" : "Unauthorized user"
                    }
                    """);

            return false;
        }
        return true;
    }
}
