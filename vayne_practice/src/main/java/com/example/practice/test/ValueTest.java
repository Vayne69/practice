package com.example.practice.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 10:42
 */
@Component
@PropertySource(value = "classpath:properties/vayne.properties")
@ConfigurationProperties(prefix = "vayne.redis")
@Data
public class ValueTest {
    // @Value("${vayne.redis.host}")
    private String host;
    // @Value("${vayne.redis.port}")
    private String port;

}
