package com.june.start.exception;

import com.june.start.common.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

/**
 * @author Douzi
 */
@RestControllerAdvice
public class MyExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    public R hasException(RuntimeException e) {
//        System.out.println(e.getMessage());
//        return R.error("系统未知异常");
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R hasValidException(MethodArgumentNotValidException e) {
        return R.error(e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public R hasParseException(DateTimeParseException e) {
        return R.error("日期格式错误，正确日期格式应为yyyy-MM-dd，例如2021-03-23");
    }

    @ExceptionHandler(ParamIllegalException.class)
    public R hasParseException(ParamIllegalException e) {
        return R.error("开始日期应当早于截止日期");
    }
}
