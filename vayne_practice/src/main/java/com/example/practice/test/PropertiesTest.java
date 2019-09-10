package com.example.practice.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 13:26
 */
@Component
@PropertySource(value = "classpath:properties/wechar_sdk.properties")
@ConfigurationProperties(prefix = "wechar")
@Data
public class PropertiesTest {
    /**
     * 模板的编号.
     */
    private String template;
    /**
     * Token.
     */
    private String token;
    /**
     * appid.
     */
    private String appid;
    /**
     * appsecret
     */
    private String appsecret;
    /**
     * 点击模板跳转的地址
     */
    private String url;
    /**
     * 授权回调地址
     */
    private String redirectUri;
    /**
     * 天使打赏前端url
     */
    private String weixinUrl;
}
