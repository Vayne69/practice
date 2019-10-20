package com.forte.util.loader;

/**
 * 直译：分支结果
 * 代表结果有着成功与否
 * @since JDK1.8
 **/
public interface BranchResult<T> extends Result<T> {

    /**
     * 判断是否成功
     * @return
     */
    Boolean isSuccess();

    /**
     * 如果失败，为何失败
     * @return
     */
    Exception why();

}
