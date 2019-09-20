package com.example.practice.security.controller;

import com.example.practice.security.model.Msg;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yang Jian
 * @date : 2019/9/11 19:13
 */
@RestController
public class HomeController {
    @GetMapping("/")
    String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
}
