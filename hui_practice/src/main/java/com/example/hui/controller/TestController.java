package com.example.hui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yang Jian
 * @date : 2019/9/8 15:04
 */
@RestController
@RequestMapping
@Slf4j
public class TestController {

    @GetMapping("/test/part")
    public String get() throws InterruptedException {
        log.info("test ......");
        Thread.sleep(32100);
        return "Helli world!!!";
    }

    @RequestMapping("/timeout/test")
    public String timeout() throws InterruptedException {
        log.info("invoking timeout endpoint .......");
        // Thread.sleep(3000);
        return "success";
    }
}
