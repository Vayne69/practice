package com.example.practice.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 11:35
 */
@Slf4j
@Data
public class WxProperties {
    public static final String WECHAR_INFO = "properties/wechar_sdk.properties";

    /**
     * 配置文件中的模板的编号.
     */

    public static final String TEMPLATE = "wechar.template";

    /**
     * 配置文件中的Token.
     */

    public static final String TOKEN = "wechar.token";

    /**
     * 配置文件中的appid.
     */

    public static final String APPID = "wechar.appID";

    /**
     * 配置文件中的appsecret
     */

    public static final String APP_SECRET = "wechar.appSecret";

    /**
     * 配置文件中的url
     */

    public static final String URL = "wechar.url";

    /**
     * 微信支付：授权回调地址
     */

    public static final String REDIRECT_URI = "wechar.redirectUri";

    /**
     * 公众号前端url
     */

    public static final String WEIXIN_URL = "wechar.weixinUrl";
    /**
     * 操作对象.
     */

    private static WxProperties config = new WxProperties();

    /**
     * 属性文件对象.
     */

    private Properties properties;

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

    /**
     * 获取config对象.
     *
     * @return
     */

    public static WxProperties getConfig() {

        return config;

    }

    private void loadPropertiesFromSrc() {
        InputStream inputStream = null;
        try {
            log.info("从classpath: " + WxProperties.class.getClassLoader().getResource("").getPath() + " 获取属性文件" + WECHAR_INFO);
            log.info("从classpath: " + Objects.requireNonNull(WxProperties.class.getClassLoader().getResource("")).getPath() + " 获取属性文件" + WECHAR_INFO);
            inputStream = WxProperties.class.getClassLoader().getResourceAsStream(WECHAR_INFO);
            if (inputStream != null) {
                properties = new Properties();
                properties.load(inputStream);
            } else {
                log.error(WECHAR_INFO + "属性文件未能在classpath指定的目录下 " + WxProperties.class.getClassLoader().getResource("").getPath() + " 找到!");
                return;
            }
            loadProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 根据传入的  对象设置配置参数
     *
     * @param pro
     */

    private void loadProperties(Properties pro) {

        log.info("开始从属性文件中加载配置项");
        String value = null;
        value = pro.getProperty(TEMPLATE);
        if (!StringUtils.isEmpty(value)) {
            this.template = value.trim();
            log.info("配置项：模板的编号==>" + TEMPLATE + "==>" + value + " 已加载");
        }
        value = pro.getProperty(TOKEN);
        if (!StringUtils.isEmpty(value)) {
            this.token = value.trim();
            log.info("配置项：Token==>" + TOKEN + "==>" + value + " 已加载");
        }
        value = pro.getProperty(APPID);
        if (!StringUtils.isEmpty(value)) {
            this.appid = value.trim();
            log.info("配置项：开发者编号==>" + APPID + "==>" + value + " 已加载");
        }
        value = pro.getProperty(APP_SECRET);
        if (!StringUtils.isEmpty(value)) {
            this.appsecret = value.trim();
            log.info("配置项：开发者密码==>" + APP_SECRET + "==>" + value + " 已加载");
        }
        value = pro.getProperty(URL);
        if (!StringUtils.isEmpty(value)) {
            this.url = value.trim();
            log.info("配置项：模板的请求地址==>" + URL + "==>" + value + " 已加载");
        }
        value = pro.getProperty(REDIRECT_URI);
        if (!StringUtils.isEmpty(value)) {
            this.redirectUri = value.trim();
            log.info("配置项：授权回调地址==>" + REDIRECT_URI + "==>" + value + " 已加载");
        }
        value = pro.getProperty(WEIXIN_URL);
        if (!StringUtils.isEmpty(value)) {
            this.weixinUrl = value.trim();
            log.info("配置项：前端URL==>" + WEIXIN_URL + "==>" + value + " 已加载");
        }
    }

    /**
     * 从properties文件加载
     *
     * @param rootPath 不包含文件名的目录.
     */

    private void loadPropertiesFromPath(String rootPath) {
        if (StringUtils.isNotBlank(rootPath)) {
            log.info("从路径读取配置文件: " + rootPath + File.separator + WECHAR_INFO);
            File file = new File(rootPath + File.separator + WECHAR_INFO);
            InputStream in = null;
            if (file.exists()) {
                try {
                    in = new FileInputStream(file);
                    properties = new Properties();
                    properties.load(in);
                    loadProperties(properties);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    if (null != in) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            } else {
                // 由于此时可能还没有完成LOG的加载，因此采用标准输出来打印日志信息
                log.error(rootPath + WECHAR_INFO + "不存在,加载参数失败");
            }
        } else {
            loadPropertiesFromSrc();
        }
    }

    public static void main(String[] args) {
        WxProperties wxProperties = new WxProperties();
        wxProperties.loadPropertiesFromPath(null);
    }
}
