package com.example.leetcode.part5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author : Yang Jian
 * @date : 2021/6/9 0009 21:52
 */
public class FooBar {
    private int n;
    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread foo1 = new Thread(() -> {
            System.out.print("Foo");
        });
        Thread bar1 = new Thread(() -> {
            System.out.print("Bar");
        });
        FooBar fooBar = new FooBar(6);

        new Thread(() -> {
            try {
                fooBar.foo(foo1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(bar1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
