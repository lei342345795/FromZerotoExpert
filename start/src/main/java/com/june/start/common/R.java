package com.june.start.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

/**
 * @author Douzi
 */
@Data
public class R {
    private String code;
    private String message;
    private HashMap<String, Object> data;


    public R(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static R error() {
        return new R(ErrorCodeEnum.FAILED.getCode(), ErrorCodeEnum.FAILED.getDesc());
    }
    public static R error(String message) {
        return new R(ErrorCodeEnum.FAILED.getCode(), message);
    }

    public static R ok() {
        return new R(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getDesc());
    }

    public void put(String key, Object value) {
        this.data.put(key, value);
    }
}
