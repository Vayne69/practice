package com.example.zheng;

import cn.sunline.error.ErrorCenter;
import com.example.zheng.controller.User;
import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ZhengPracticeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZhengPracticeApplication.class, args);
    }

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ErrorCenter errorCenter;

    @Override
    public void run(String... args) throws Exception {
        ErrorCenter errorCenter = applicationContext.getBean(ErrorCenter.class);
        System.out.println(errorCenter.getReturn("aaa", "aaaa", "aaaa"));

    }
}
