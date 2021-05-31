package com.example.logback.runnable;

/**
 * @author : Yang Jian
 * @date : 2020/10/8 0008 10:37
 */
public class Test {
    private final static Object lock = new Object();

    private static void work() {
        System.out.println("Begin ...");
        synchronized (lock) {
            System.out.println("start ...");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end ...");
        }
    }

    public static void main(String[] args) {
        new Thread(Test::work).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            lock.notify();
        }
    }
}
