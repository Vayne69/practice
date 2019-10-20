package com.forte.util.loader;

/**
 * 方法的返回结果
 * @since JDK1.8
 **/
public interface Result<T> {


    /**
     * 获取结果
     * @return 结果
     */
    T getResult();


}
