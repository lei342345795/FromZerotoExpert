package com.june.start.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Douzi
 */
@RestController
@RequestMapping("/FromZerotoExpert")
public class StartController {
    @GetMapping
    public String getHello(@CookieValue(value = "isNew", defaultValue = "yes") String user, HttpServletResponse response) {
        if (user != null && "yes".equals(user)) {
            Cookie cookie = new Cookie("isNew", "no");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return "嗨，欢迎您来到 from zero to expert.";
        } else if ("no".equals(user)) {
            return "嗨，欢迎您再次来到 from zero to expert.";
        }
        return null;
    }
}
