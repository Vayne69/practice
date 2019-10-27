package com.example.swagger.controller;

import com.example.swagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author : Yang Jian
 * @date : 2019/10/27 0027 10:33
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的rest api")
public class UserController {
    @ApiOperation("新增用户接口")
    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        return false;
    }

    @ApiOperation("通过id查找用户接口")
    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return new User();
    }

    @ApiOperation("更新用户信息接口")
    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping("/delete/{id}")
    // @ApiIgnore
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }
}
