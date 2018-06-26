package com.c503.exception;

/**
 * 描述:
 * 自定义异常类
 *
 * @author liumm
 * @date 2018-06-09 18:22
 */
public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

}
