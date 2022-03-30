package com.example.excledemo.model;

import java.io.IOException;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/3/23 16:46
 */
public class ThreadTest {
    private int num = 1;

    public ThreadTest() {
        new Thread(() -> {
            System.out.println(this.num);
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new ThreadTest();
        System.in.read();
    }
}
