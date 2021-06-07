package com.example.leetcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class LeetcodeApplication implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(LeetcodeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    System.out.println("==="+ j +"=====");
                    stringRedisTemplate.opsForValue().increment("aaa");
                    System.out.println("======处理完======="+ j +"=====");
                }
            }).start();
        }
    }
}
