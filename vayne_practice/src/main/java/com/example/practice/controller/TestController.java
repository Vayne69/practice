package com.example.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yang Jian
 * @date : 2019/9/8 15:04
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/test")
    public String get() {
        return "Helli world!!!";
    }
}
