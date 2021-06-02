package com.example.leetcode.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Yang Jian
 * @date : 2021/6/2 0002 21:46
 */
public class ThreadPools2 {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(16);
    public static void submit(Runnable runnable){
        executorService.submit(runnable);
    }
    public static void shutdown(){
        executorService.shutdown();
    }
}
