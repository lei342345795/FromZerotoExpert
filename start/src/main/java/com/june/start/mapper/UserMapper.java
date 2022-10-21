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
    @Insert("insert into user (user_name, user_pwd)values (#{userName}, #{userPwd})")
    void add(User user);

    @Select("select count(user_name) from user where user_name = #{userName}")
    int isDuplicated(String userName);
}
