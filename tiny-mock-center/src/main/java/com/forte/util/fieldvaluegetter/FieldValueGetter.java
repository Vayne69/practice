package com.forte.util.fieldvaluegetter;

/**
 * 字段值获取器
 *
 * @author Administrator
 */
@FunctionalInterface
public interface FieldValueGetter<T> {

    /**
     * 获取这个字段的参数
     *
     * @return
     */
    T value();

}
