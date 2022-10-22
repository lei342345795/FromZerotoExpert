package com.june.start.domain;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Douzi
 */
@Data
public class User {


    private int userId;
    @NotBlank(message = "用户名不能为空")
    @Length(max = 15, message = "用户名长度不能超过15位")
    private String userName;
    @Pattern(regexp = "^(?!\\d+$)(?![a-z]+$)(?![A-Z]+$)[\\da-zA-z]{6,16}$", message = "密码长度应该在6-16位，且至少包含两种字符")
    private String userPwd;
    private String userPhone;
    private String userEmail;

    public User(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }
}
