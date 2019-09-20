package com.example.practice.security.model;

import lombok.Data;

@Data
public class SysPermission {
    private Integer id;

    private String name;

    private String description;

    private String url;

    private Integer pid;
}