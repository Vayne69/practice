package com.forte.util.exception;

/**
 * 参数数量不符异常
 *
 * @author Administrator
 */
public class ParameterSizeException extends MockException {
    public ParameterSizeException() {
        super("参数数量与方法参数数量不符！");
    }
}
