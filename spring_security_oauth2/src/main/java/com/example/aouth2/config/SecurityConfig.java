package com.example.aouth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : Yang Jian
 * @date : 2019/9/26 0026 22:06
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhs")
                .password("$2a$10$kwLIAqAupvY87OM.O25.Yu1QKEXV1imAv7jWbDaQRFUFWSnSiDEwG")
                .roles("admin")
                .and()
                .withUser("lis")
                .password("$2a$10$kwLIAqAupvY87OM.O25.Yu1QKEXV1imAv7jWbDaQRFUFWSnSiDEwG")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("oauth/**")
                .authorizeRequests()
                .antMatchers("oauth/**").permitAll()
                .and().csrf().disable();
    }
}
