package com.forte.util.mockbean;

import java.util.Map;
import java.util.Optional;

/**
 *
 * Map类型的结果集合
 *
 * @author Administrator
 */
public class MockMapObject implements MockObject<Map<String, Object>> {

    private final MockMapBean mockMapBean;

    /**
     * 返回获取结果的Optional封装类
     *
     * @return
     */
    @Override
    public Optional<Map<String, Object>> get() {
        return Optional.ofNullable(mockMapBean.getObject());
    }


    /**
     * 唯一构造
     * @param mockMapBean
     */
    public MockMapObject(MockMapBean mockMapBean) {
        this.mockMapBean = mockMapBean;
    }

}
