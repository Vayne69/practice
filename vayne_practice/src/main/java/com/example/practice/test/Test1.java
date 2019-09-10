package com.example.practice.test;

import org.antlr.v4.runtime.misc.IntegerList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Yang Jian
 * @date : 2019/9/8 17:17
 */
public class Test1 {
    public static void main(String[] args) {
        final List<Integer> list = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.stream().parallel().forEach(i -> {
            System.out.println(i);
            list.add(i);
        });
        System.out.println(list);
    }

}
