package com.forte.util.mockbean;

import java.util.Optional;

/**
 * 将{@link MockBean}封装并返回
 *
 * @author Administrator
 * @since JDK1.8
 **/
public class MockNormalObject<T> implements MockObject<T> {

    private final MockBean<T> mockBean;

    /**
     * 返回获取结果的Optional封装类
     * @return
     */
    @Override
    public Optional<T> get() {
        return Optional.ofNullable(mockBean.getObject());
    }


    /**
     * 唯一构造
     * @param mockBean
     */
    public MockNormalObject(MockBean<T> mockBean) {
        this.mockBean = mockBean;
    }
}
