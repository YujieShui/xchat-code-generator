package com.shuiyujie.generator.utils;

import java.io.File;
import java.io.IOException;

/**
 * created by shui 2017/8/20
 */
public class FileUtil {

    /**
     * 写字串到文件中，若文件不存在，则建立新文件
     *
     * @param file
     *            需要写的文件的路径
     * @param data
     *            需要写入的字串e
     * @param encoding
     *            文件编码，默认为UTF-8
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
}
