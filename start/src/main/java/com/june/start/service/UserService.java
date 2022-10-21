package com.june.start.service;

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
}
