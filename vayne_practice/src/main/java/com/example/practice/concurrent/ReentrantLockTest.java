package com.example.practice.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Yang Jian
 * @date : 2019/9/4 19:31
 */
public class ReentrantLockTest {
    @Test
    public void lockTest() {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        while (true) {
            executor.execute(ReentrantLockTest::tryLockTest);
        }
    }

    @Test
    public static void tryLockTest() {
        Lock lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // lock.unlock();
            }
        } else {
            doOtherThings();
        }
    }

    private static void doSomething() {
        System.out.println(Thread.currentThread().getName() + "成功获取锁！");
    }

    private static void doOtherThings() {
        System.out.println(Thread.currentThread().getName() + "未获取锁，转而做其它事！");
    }
}
