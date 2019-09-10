package com.example.practice.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author : Yang Jian
 * @date : 2019/9/9 20:50
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("用户的用户名：{}" + s);
        User user = new User(s, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
