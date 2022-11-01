package com.june.start.exception;

/**
 * @author Douzi
 */
public class FormatException extends RuntimeException{
    private String message;
    public FormatException(String message){
        super(message);
        this.message = message;
    }

}
