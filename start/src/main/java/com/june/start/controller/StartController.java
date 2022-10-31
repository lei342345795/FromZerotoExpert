package com.june.start.controller;

import com.june.start.common.R;
import com.june.start.common.vo.StartVo;
import com.june.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * @author Douzi
 */
@RestController
@RequestMapping("/init")
public class StartController {


}



//    @GetMapping
//    public String getHello(@CookieValue(value = "isNew", defaultValue = "yes") String user, HttpServletResponse response) {
//        if (user != null && "yes".equals(user)) {
//            Cookie cookie = new Cookie("isNew", "no");
//            cookie.setMaxAge(24 * 60 * 60);
//            response.addCookie(cookie);
//            return "嗨，欢迎您来到 from zero to expert.";
//        } else if ("no".equals(user)) {
//            return "嗨，欢迎您再次来到 from zero to expert.";
//        }
//        return null;
//    }

