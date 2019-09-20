package com.example.practice.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Yang Jian
 * @date : 2019/9/19 11:26
 */
public class ReentrantLockTest {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final int limit;
    private volatile int count;

    public ReentrantLockTest(int limit, int count) {
        this.limit = limit;
        this.count = count;
    }

    private void test() {
        lock.lock();
        try {
            while (count < limit) {
                try {
                    System.out.println(String.format("线程[%s]打印数字:%d", Thread.currentThread().getName(), ++count));
                    Thread.sleep(1000);
                    condition.signalAll();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest(10, 0);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(2, 3, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), threadFactory);
        executorService.execute(lockTest::test);
        executorService.execute(lockTest::test);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
