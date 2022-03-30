package com.example.excledemo.service;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/3/24 10:40
 */
public class PeopleFactory {
    private static Map<String, People> map = new HashMap<>();

    public static People getByUserType(String type) {
        return map.get(type);
    }

    public static void register(String userType, People userPayService) {
        Assert.notNull(userType, "userType can't be null");
        map.put(userType, userPayService);
    }
}
