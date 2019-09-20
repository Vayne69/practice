package com.example.practice.security.config;

import com.example.practice.security.service.MyFilterSecuretyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author : Yang Jian
 * @date : 2019/9/9 20:45
 */
// @Configuration
// @EnableWebSecurity
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyFilterSecuretyInterceptor myFilterSecurityInterceptor;
    @Autowired
    UserDetailsService customUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .loginPage("/login.html")
                // .loginProcessingUrl("/user/login")
                // .and() //  定义当需要用户登录时候，转到的登录页面。
                // .authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
                // .antMatchers("/login.html").permitAll()
                // .anyRequest() // 任何请求,登录后可以访问
                // .authenticated()
                // .and()
                // .csrf().disable();
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
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
