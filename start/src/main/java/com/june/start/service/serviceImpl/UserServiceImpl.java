package com.june.start.service.serviceImpl;

import com.june.start.common.po.LoginPo;
import com.june.start.mapper.UserMapper;
import com.june.start.domain.User;
import com.june.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Douzi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public boolean isDuplicated(String userName) {
        int count = userMapper.isDuplicated(userName);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public LoginPo getLogin(String userName) {
        return userMapper.getLogin(userName);
    }

    @Override
    public String getUserName(int userId) {
        return userMapper.getUserName(userId);
    }


}
