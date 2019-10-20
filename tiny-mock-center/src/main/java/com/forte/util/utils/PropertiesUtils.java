package com.forte.util.utils;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : Yang Jian
 * @date : 2019/10/16 16:39
 */

public class PropertiesUtils {

    /**
     * 根据key读取value
     *
     * @param pathName
     * @return
     */
    public static Properties readValue(String pathName) throws IOException {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream(pathName);
            if (in != null) {
                prop.load(in);
            }
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
}
