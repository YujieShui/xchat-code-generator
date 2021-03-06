package com.shuiyujie.generator.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串操作工具类
 * created by shui 2017/8/20
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串首字母转换成大写
     *
     * @param str
     * @return
     */
    public static String upcaseFirst(String str) {
        if (isEmpty(str)) {
            return null;
        }
        if (1 == str.length()) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将字符串拆分为list
     * 数据字段根据 "_" 分割
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<String> splitStr2List(String str, String regex) {
        List<String> list = new ArrayList<String>();
        String[] strs = str.split(regex);
        for (String s : strs) {
            list.add(s.trim());
        }
        return list;
    }

    /**
     * 数据库字段转换成驼峰法命名的字段
     * eg: org_id --> orgId
     *
     * @param list
     * @return
     */
    public static String str2Column(List<String> list) {
        String str = "";

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                str += upcaseFirst(list.get(i));
            } else {
                str += list.get(i);
            }
        }

        return str;
    }

    /**
     * 数据库名转换成类名,首字母大写
     * xc_user --> XcUser
     *
     * @param tableName
     * @return
     */
    public static String tableName2ClassName(String tableName) {

        return upcaseFirst(str2Column(splitStr2List(tableName, "_")));

    }

    /**
     * 数据库字段名转换成Java属性名,首字母小写
     * user_id --> userId
     *
     * @param
     * @return
     */
    public static String dbColumn2ClassColumn(String column) {

        return str2Column(splitStr2List(column, "_"));

    }

    /**
     * 数据库类型转换java类型
     *
     * @param type
     * @return
     */
    public static String typeTransfer(String type) {

        if (type.equals("int")) {
            return "Long";
        } else if (type.equals("tinyint")) {
            return "Integer";
        } else if (type.equals("varchar")) {
            return "String";
        } else if (type.equals("datetime")) {
            return "Date";
        } else if (type.equals("bigint")) {
            return "Long";
        } else {
            return type;
        }

    }

    /**
     * 数据库类型转换成 pb 类型
     * @param type
     * @return
     */
    public static String typeTransfer4Pb(String type) {

        if(type.equals("varchar")){
            return "string";
        }else {
            return "int64";
        }

    }

    public static void main(String[] args) {
        String str = "userVO";
        System.out.println(upcaseFirst(str));

        String str2 = "user_org_id";
        List<String> list = splitStr2List(str2, "_");
        System.out.println(splitStr2List(str2, "_"));

        String str3 = str2Column(list);
        System.out.println(str3);

    }

}
