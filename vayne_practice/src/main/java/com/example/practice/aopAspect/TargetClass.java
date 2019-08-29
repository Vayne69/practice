package com.example.practice.aopAspect;

import org.springframework.stereotype.Component;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 19:49
 */
@Component
public class TargetClass {
    public String joint(String str1, String str2) {
        System.out.println("###########");
        return "+++++" + str1 + "********" + str2 + "#####";
    }
}
