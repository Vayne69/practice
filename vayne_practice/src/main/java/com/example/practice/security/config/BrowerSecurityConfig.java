package com.example.practice.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : Yang Jian
 * @date : 2019/9/9 20:45
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and() //  定义当需要用户登录时候，转到的登录页面。
                .authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest() // 任何请求,登录后可以访问
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("12345").roles("SuperAdmin")
                .and()
                .withUser("lisi").password("12345").roles("Admin")
                .and()
                .withUser("wangwu").password("12345").roles("Employee")
                .and()
                .passwordEncoder(new MyPassWordEncoder());

    }
}
