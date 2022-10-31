package com.june.start.exception;

import com.june.start.common.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
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
}
