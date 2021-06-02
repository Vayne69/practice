package com.example.leetcode.part1;

/**
 * @author : Yang Jian
 * @date : 2021/6/2 0002 21:38
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        try {
            Thread1 thread1 = new Thread1();
            ThreadPools.submit(thread1);
        } finally {
            ThreadPools.shutdown();
        }
    }
}
