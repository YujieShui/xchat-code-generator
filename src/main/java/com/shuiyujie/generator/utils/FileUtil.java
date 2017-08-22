package com.shuiyujie.generator.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * created by shui 2017/8/20
 */
public class FileUtil {

    /**
     * 判断文件和目录是否存在
     * @param filePath
     * @return
     */
    public static boolean exists(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 递归创建目录
     *
     * @param directory
     * @return
     */
    public static boolean createDirectory(String directory) {
        try {
            FileUtils.forceMkdir(new File(directory));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 清空文件夹下的文件，不删除文件夹
     * @param directory
     * @return
     */
    public static boolean cleanDirectory(File directory) {
        try {
            FileUtils.cleanDirectory(directory);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写字串到文件中，若文件不存在，则建立新文件
     *
     * @param file     需要写的文件的路径
     * @param data     需要写入的字串e
     * @param encoding 文件编码，默认为UTF-8
     * @return true:写入成功 false:写入失败
     */
    public static boolean writeToFile(String file, String data, String encoding) {
        try {
            if (encoding == null || "".equals(encoding)) {
                encoding = "UTF-8";
            }
            org.apache.commons.io.FileUtils.writeStringToFile(new File(file), data, encoding);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在指定文件夹中创建指定文件
     * @param filePath  文件目录
     * @param filePathName  文件全路径 文件目录+文件名
     * @return
     */
    public static boolean createNewFile(String filePath,String filePathName){
        // 文件目录不存在则先创建文件目录
        if(!FileUtil.exists(filePath)){
            if(!createDirectory(filePath)){
                return false;
            }
        }
        // 创建目录成功创建文件
        try {
            File file = new File(filePathName);
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
