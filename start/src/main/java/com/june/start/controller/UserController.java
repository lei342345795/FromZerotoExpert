package com.june.start.controller;

import com.june.start.common.R;
import com.june.start.common.utils.MyUtils;
import com.june.start.domain.User;
import com.june.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Douzi
 */
@RestController
@RequestMapping("/Register")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
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
}
