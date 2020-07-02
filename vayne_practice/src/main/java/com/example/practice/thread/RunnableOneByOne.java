package com.example.practice.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author : Yang Jian
 * @date : 2020/7/2 09:04
 */
public class RunnableOneByOne {
    public final static LinkedList<String> CONTROLLER = new LinkedList<>();

    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<>();
        Stream.of("m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9", "m10")
                .map(RunnableOneByOne::createThread)
                .forEach(thread -> {
                    thread.start();
                    worker.add(thread);
                });
        worker.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("all of capture is finished").ifPresent(System.out::println);
    }

    public static Thread createThread(String name) {
        return new Thread(() -> {
            Optional.of("the capture is start======" + Thread.currentThread().getName()).ifPresent(System.out::println);
            synchronized (CONTROLLER) {
                while (CONTROLLER.size() >= 5) {
                    try {
                        CONTROLLER.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLLER.addLast("aaaaa");
            }
            Optional.of("the capture is working======" + Thread.currentThread().getName()).ifPresent(System.out::println);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLLER) {
                Optional.of("the capture is end======" + Thread.currentThread().getName()).ifPresent(System.out::println);
                CONTROLLER.removeFirst();
                CONTROLLER.notifyAll();
            }
        }, name);
    }
}
