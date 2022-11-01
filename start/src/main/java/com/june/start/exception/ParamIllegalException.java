package com.june.start.exception;

/**
 * @author Douzi
 */
public class ParamIllegalException extends Exception{
    private String message;
    public ParamIllegalException(String message){
        super(message);
        this.message = message;
    }

    public ParamIllegalException() {
        super();
    }
}
