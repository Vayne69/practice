package com.example.practice.security.dao;

import com.example.practice.security.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> findAll();

    List<SysPermission> findByAdminUserId(int userId);
}