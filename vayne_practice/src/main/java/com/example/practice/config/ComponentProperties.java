package com.example.practice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 10:08
 */
@Component
@ConfigurationProperties(prefix = "local")
@Data
public class ComponentProperties {
    private String host;
    private String port;
}
