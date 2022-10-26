package com.june.start.mapper;

import com.june.start.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Douzi
 */
@Mapper
public interface UserMapper {
    /**
     * 加入新用户
     *
     * @param user 新用户信息
     */
    @Insert("insert into user (user_name, user_pwd)values (#{userName}, #{userPwd})")
    void add(User user);

    /**
     * 查询用户名是否已经在数据库中存在
     *
     * @param userName 用户名
     * @return 返回数据库中相同用户名的个数，0表示不存在，1表示存在
     */
    @Select("select count(user_name) from user where user_name = #{userName}")
    int isDuplicated(String userName);

    /**
     * 根据用户名查询密码用来验证登陆
     * @param userName 用户名
     * @return 加密后的密码
     */

    @Select("select user_pwd from user where user_name = #{userName}")
    String getPwd(String userName);
}
