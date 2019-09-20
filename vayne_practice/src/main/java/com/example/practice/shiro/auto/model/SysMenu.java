package com.example.practice.shiro.auto.model;

import lombok.Data;

@Data
public class SysMenu {
    /**
     * 权限ID
     */
    private Long menuId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String perms;
}