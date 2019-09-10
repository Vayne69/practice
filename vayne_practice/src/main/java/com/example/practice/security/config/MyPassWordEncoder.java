package com.example.practice.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author : Yang Jian
 * @date : 2019/9/9 21:00
 */
public class MyPassWordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
