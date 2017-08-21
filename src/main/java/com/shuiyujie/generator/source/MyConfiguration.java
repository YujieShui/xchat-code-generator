package com.shuiyujie.generator.source;

import com.shuiyujie.generator.utils.PropertyUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 获取数据库中指定表的信息
 * created by shui 2017/8/19
 */
public class Configuration {

    private static Map<String,String> items = new HashMap<>();

    private static String CONFIG_PROPERTIES_NAME = "config.properties";

    static{
        loadConfig();
    }

    private static void loadConfig(){

        PropertyUtil.loadProp(CONFIG_PROPERTIES_NAME);

        Set keyValue = PropertyUtil.prop.keySet();
        for (Iterator it = keyValue.iterator(); it.hasNext();) {
            String key = (String) it.next();
            String value = PropertyUtil.prop.getProperty(key);
            items.put(key,value);
        }
    }


    /**
     * 获得字串配置值
     *
     * @param name
     * @return
     */
    public static String getString(String name) {
        String value = (String) items.get(name);
        return (value == null) ? "" : value;
    }

    public static void main(String[] args) {

        Iterator<Map.Entry<String, String>> entries = items.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

//
//            for (String key : items.keySet()) {
//            String value = items.get(key);
//            System.out.println(key + "---" + value);
//        }
    }

}
