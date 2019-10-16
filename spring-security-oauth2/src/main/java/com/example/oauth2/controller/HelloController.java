package com.example.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yang Jian
 * @date : 2019/9/23 11:37
 */
@RestController
public class HelloController {
    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin!";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
