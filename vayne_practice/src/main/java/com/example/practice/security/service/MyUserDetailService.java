package com.example.practice.security.service;

import com.example.practice.security.dao.SysPermissionMapper;
import com.example.practice.security.dao.SysUserMapper;
import com.example.practice.security.model.SysPermission;
import com.example.practice.security.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Yang Jian
 * @date : 2019/9/9 20:50
 */
@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysPermissionMapper sysPermissionMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户的用户名：{}" + username);
        SysUser user = sysUserMapper.findByUserName(username);
        if (user != null) {
            List<SysPermission> permissions = sysPermissionMapper.findByAdminUserId(user.getUserId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            permissions.forEach(permission -> {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
