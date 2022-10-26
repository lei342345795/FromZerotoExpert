package com.june.start.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Douzi
 */
@Data
public class User implements Serializable {


    private int userId;
    @NotBlank(message = "用户名不能为空")
    @Length(max = 15, message = "用户名长度不能超过15位")
    private String userName;
    @Pattern(regexp = "^(?!\\d+$)(?![a-z]+$)(?![A-Z]+$)[\\da-zA-z]{6,16}$", message = "密码不符合规则")
    private String userPwd;
    private String userPhone;
    private String userEmail;

    public User(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }
}
