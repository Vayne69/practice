package com.example.practice.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.example.practice.security.dao.SysPermissionMapper;
import com.example.practice.security.model.SysPermission;

/**
 * @author dell
 */
@Service
public class SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    public int insert(SysPermission record) {
        return sysPermissionMapper.insert(record);
    }


    public int insertSelective(SysPermission record) {
        return sysPermissionMapper.insertSelective(record);
    }

}
