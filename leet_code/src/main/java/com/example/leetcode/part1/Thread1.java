package com.example.leetcode.part1;

/**
 * @author : Yang Jian
 * @date : 2021/6/2 0002 21:35
 */
public class Thread1 extends Thread{
    @Override
    public void run() {
        Thread aaaaaaaaaa = new Thread(() -> {
            System.out.println("aaaaaaaaaa");
        });
        try {
            ThreadPools2.submit(aaaaaaaaaa);
            System.out.println("bbbbbbbbbbbbb");
        } finally {
            ThreadPools2.shutdown();
        }
    }
}
