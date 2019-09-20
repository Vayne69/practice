package com.example.zheng.controller;

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

    @RequestMapping("/timeout/test")
    public String timeout() throws InterruptedException {
        log.info("invoking timeout endpoint .......");
        Thread.sleep(20000);
        return "success";
    }
}
