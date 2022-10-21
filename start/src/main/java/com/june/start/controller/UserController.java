package com.june.start.controller;

import com.june.start.common.R;
import com.june.start.domain.User;
import com.june.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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
        boolean duplicated = userService.isDuplicated(userName);
        if (!duplicated) {
            String password = DigestUtils.md5DigestAsHex(user.getUserPwd().getBytes());
            user.setUserPwd(password);
            userService.add(user);
            return R.ok();
        } else {
            return R.error("用户名重复");
        }

    }
}
