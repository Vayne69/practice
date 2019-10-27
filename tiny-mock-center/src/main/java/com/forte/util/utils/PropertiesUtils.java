package com.forte.util.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Yang Jian
 * @date : 2019/10/16 16:39
 */

public class PropertiesUtils {
    private final static Map<String, Properties> HASH_MAP = new ConcurrentHashMap<>();

    /**
     * 根据key读取value
     *
     * @param pathName
     * @return
     */
    public static Properties loadProperties(String pathName) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream(pathName);
            if (in != null) {
                prop.load(in);
            }
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
            return prop;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private PropertiesUtils() {
    }

    public static Properties readValue(String pathName) {
        if (pathName == null || pathName.trim().length() == 0) {
            return new Properties();
        }
        Properties properties = HASH_MAP.get(pathName);
        if (properties == null) {
            synchronized (pathName.intern()) {
                properties = HASH_MAP.get(pathName);
                if (properties == null) {
                    properties = loadProperties(pathName);
                    HASH_MAP.put(pathName, properties);
                }
            }
        }
        return properties;
    }

    public static void clear() {
        HASH_MAP.clear();
    }
}
