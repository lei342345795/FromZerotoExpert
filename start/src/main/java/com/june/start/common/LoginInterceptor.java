package com.june.start.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Douzi
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String sessionAttribute = (String) session.getAttribute("LoginStatus");
        if (sessionAttribute != null) {
            String userName = redisTemplate.opsForValue().get(sessionAttribute);
            if (userName != null) {
                return true;
            }
        } else {
            try {
                //TODO: 上线前更改重定向url
                response.sendRedirect("http://localhost:8080/FromZerotoExpert/login.html");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
