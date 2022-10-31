package com.june.start.service;

import com.june.start.common.po.LoginPo;
import com.june.start.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author Douzi
 */
@Service
public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    /**
     * 判断用户名是否重复
     * @param userName
     * @return 返回true表示用户名重复，否则用户名不重复可以使用
     */
    boolean isDuplicated(String userName);

    /**
     * 根据用户名获取加密后的密码和用户id
     * @param userName
     * @return 加密后的密码和用户id封装成的Po
     */
    LoginPo getLogin(String userName);

    /**
     * 根据用户id获取用户名
     * @param userId 用户id
     * @return 用户名
     */
    String getUserName(int userId);
}
