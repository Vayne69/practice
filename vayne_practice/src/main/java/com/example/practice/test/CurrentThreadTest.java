package com.example.practice.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author : Yang Jian
 * @date : 2019/9/19 10:25
 */
public class CurrentThreadTest {
    private final Object object = new Object();
    private final int index;
    private volatile int count;

    public CurrentThreadTest(int index, int count) {
        this.index = index;
        this.count = count;
    }

    public void test() {
        synchronized (object) {
            while (count < index) {
                try {
                    System.out.println(String.format("线程[%s]打印数字:%d", Thread.currentThread().getName(), ++count));
                    // Thread.sleep(1000);
                    object.notifyAll();
                    object.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CurrentThreadTest currentThreadTest = new CurrentThreadTest(10, 0);
        // Thread thread1 = new Thread(()->{
        //    currentThreadTest.test();
        // });
        // Thread thread2 = new Thread(()->{
        //    currentThreadTest.test();
        // });
        // thread1.start();
        // thread2.start();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(2, 3,
                1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), namedThreadFactory
        );
        executorService.execute(currentThreadTest::test);
        executorService.execute(currentThreadTest::test);
        Thread.sleep(Integer.MAX_VALUE);
        executorService.shutdown();
    }
}
