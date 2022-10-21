package com.june.start.domain;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @author Douzi
 */
@Data
public class User {


    private int userId;
    @NotBlank(message = "用户名不能为空")
    @Length(max = 8, message = "用户名长度不能超过8位")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 14, message = "密码长度应为8-14位")
    private String userPwd;
    private String userPhone;
    private String userEmail;

    public User(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }
}
