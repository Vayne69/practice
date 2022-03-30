package com.example.leetcode.part6;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author : Yang Jian
 * @date : 2021/6/15 0015 22:34
 */
public class ZeroEvenOdd {
    private int n;
    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        int i = 0;
        if (n == 0) {
            printNumber.accept(0);
        }
        while (i < n) {
            zero.acquire();
            printNumber.accept(0);
            i++;
            if (i % 2 == 0) {
                odd.release();
            } else if (i % 2 == 1) {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int i = 1;
        while (i <= n) {
            even.acquire();
            printNumber.accept(i);
            i += 2;
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int i = 2;
        while (i <= n) {
            odd.acquire();
            printNumber.accept(i);
            i += 2;
            zero.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
