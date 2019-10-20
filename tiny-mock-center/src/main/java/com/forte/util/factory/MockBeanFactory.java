package com.forte.util.factory;

import com.forte.util.mockbean.MockBean;
import com.forte.util.mockbean.MockField;
import com.forte.util.mockbean.MockMapBean;

/**
 * MockBean的工厂
 * @author Administrator
 */
public class MockBeanFactory {

    /**
     * 创建一个MockBean
     * @param objectClass
     * @param fields
     * @param <T>
     * @return
     */
    public static <T> MockBean<T> createMockBean(Class<T> objectClass, MockField[] fields) {
        return new MockBean<>(objectClass, fields);
    }

    /**
     * 创建一个MockMapBean
     * @param fields
     * @return
     */
    public static MockMapBean createMockMapBean(MockField[] fields) {
        return new MockMapBean(fields);
    }

}
