package com.forte.util.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : Yang Jian
 * @date : 2019/10/16 16:39
 */

public class PropertiesUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * 根据key读取value
     *
     * @param pathName
     * @return
     */
    public static Properties readValue(String pathName) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream(pathName);
            if (in != null) {
                prop.load(in);
            }
            return prop;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private PropertiesUtils() {
    }
}
