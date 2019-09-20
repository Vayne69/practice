package com.example.practice.security.model;

import lombok.Data;

@Data
public class SysUser {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String state;
}