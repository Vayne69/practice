package com.example.practice.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Yang Jian
 * @date : 2019/9/11 19:17
 */
@Data
@AllArgsConstructor
public class Msg {
    private String title;
    private String content;
    private String etraInfo;
}
