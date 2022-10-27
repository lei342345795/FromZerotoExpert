package com.june.start.controller;

import com.june.start.common.R;
import com.june.start.common.utils.MyUtils;
import com.june.start.domain.User;
import com.june.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author Douzi
 */
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @PostMapping("/Register")
    public R register(@Valid @RequestBody User user) {
        String userName = user.getUserName();
        if (MyUtils.isIllegal(userName)) {
            return R.error("您输入的用户名包含敏感词，请重新输入");
        }
        if (userService.isDuplicated(userName)) {
            return R.error("用户名重复");
        }
        String encode = MyUtils.getBcrypt(user.getUserPwd());
        user.setUserPwd(encode);
        userService.add(user);
        return R.ok();
    }

    @PostMapping("/Login")
    public R login(@Valid @RequestBody User user, HttpServletRequest request) {
        String userName = user.getUserName();
        String userPwd = user.getUserPwd();
        String realPwd = userService.getPwd(userName);
        if (realPwd == null) {
            return R.error("用户不存在");
        }
        boolean check = MyUtils.checkBcrypt(userPwd, realPwd);
        //密码正确
        if (check) {
            HttpSession session = request.getSession();
            String sessionAttribute = MyUtils.getRandom();
            session.setAttribute("LoginStatus", sessionAttribute);
            session.setMaxInactiveInterval(24 * 60 * 60);
            redisTemplate.opsForValue().set(sessionAttribute, userName, 1, TimeUnit.DAYS);
            redisTemplate.opsForValue().set(userName, sessionAttribute, 5,TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error("用户名或密码错误");
        }
    }
}
