package com.example.practice.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : Yang Jian
 * @date : 2019/9/8 16:34
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(3, 15, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<String> listStr = new ArrayList<>();
        List<BigDecimal> ttList = new ArrayList<>();
        BigDecimal bigDecimal = ttList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        System.out.println(bigDecimal);
        List<Double> list1 = list.stream().parallel().map(i -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            return Double.parseDouble(String.valueOf(i));
        }).collect(Collectors.toList());
        System.out.println(list1);
    }
}
