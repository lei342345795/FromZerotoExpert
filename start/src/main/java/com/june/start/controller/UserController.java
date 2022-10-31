package com.june.start.controller;

import com.june.start.common.R;
import com.june.start.common.po.LoginPo;
import com.june.start.common.utils.MyUtils;
import com.june.start.domain.User;
import com.june.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        LoginPo loginPo = userService.getLogin(userName);
        if (loginPo.getUserPwd() == null) {
            return R.error("用户不存在");
        }
        boolean check = MyUtils.checkBcrypt(userPwd, loginPo.getUserPwd());
        //密码正确
        if (check) {
            HttpSession session = request.getSession();
            String sessionAttribute = MyUtils.getRandom();
            session.setAttribute("LoginStatus", sessionAttribute);
            session.setMaxInactiveInterval(24 * 60 * 60);
            redisTemplate.opsForValue().set(sessionAttribute, loginPo.getUserId() + "", 1, TimeUnit.DAYS);
            redisTemplate.opsForZSet().add("onlineUser", loginPo.getUserId() + "", System.currentTimeMillis());
            redisTemplate.opsForValue().set("userId:" + loginPo.getUserId(), sessionAttribute, 1, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error("用户名或密码错误");
        }
    }

    @GetMapping("/Init")
    public R start(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String sessionAttribute = (String) session.getAttribute("LoginStatus");
        String userId = redisTemplate.opsForValue().get(sessionAttribute);
        String userName = userService.getUserName(Integer.parseInt(userId));
        //是否有在线用户数记录（在线用户数记录1分钟更新一次）
        String onlineNum = redisTemplate.opsForValue().get("onlineNum");
        //没有在线用户数，就统计并生成在线用户数
        if (onlineNum == null) {
            long now = System.currentTimeMillis();
            //将1分钟内有操作的用户视为在线，统计在线用户数
            onlineNum = redisTemplate.opsForZSet().count("onlineUser", now - 60000, now) + "";
            redisTemplate.opsForValue().set("onlineNum", onlineNum, 1, TimeUnit.MINUTES);
        }
        return R.ok().put("onlineNum", onlineNum).put("userName", userName);
    }
}
