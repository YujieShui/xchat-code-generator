package com.shuiyujie.generator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 * created by shui 2017/8/21
 */
public class PropertyUtil {

    public static Properties prop = new Properties();


    /**
     * 加载配置文件
     *
     * @param fileName
     */
    public static void loadProp(String fileName) {

        InputStream is = null;

        try {
            is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据 key 获取 value
     *
     * @param key
     * @return
     */
    public static String getValueByKey(String key) {

        return prop.getProperty(key);
    }

    public static void main(String[] args) {
        loadProp("config.properties");
        System.out.println(getValueByKey("driverClassName"));
    }
}
