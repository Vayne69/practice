package com.example.excledemo.exception;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/3/11 10:32
 */
public class OperationException extends RuntimeException {
    public OperationException(String msg) {
        super(msg);
    }
}
