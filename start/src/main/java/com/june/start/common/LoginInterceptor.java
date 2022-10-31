package com.june.start.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
            String userId = redisTemplate.opsForValue().get(sessionAttribute);
            if (userId != null) {
                String onlineSession = redisTemplate.opsForValue().get("userId:" + userId);
                //当前用户不在线或在线的就是当前的session
                if (onlineSession == null || sessionAttribute.equals(onlineSession)) {
                    session.setMaxInactiveInterval(24 * 60 * 60);
                    //重置redis的过期时间
                    redisTemplate.expire(sessionAttribute, 1, TimeUnit.DAYS);
                    //将用户的登陆状态改为在线，1分钟后自动离线
                    redisTemplate.expire(userId, 1, TimeUnit.MINUTES);
                    return true;
                }
            }
        }
        try {
            //TODO: 上线前更改重定向url
            response.sendRedirect("http://localhost:8080/FromZerotoExpert/login.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

