package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.source.MyConfiguration;
import com.shuiyujie.generator.utils.Constants;
import com.shuiyujie.generator.utils.FileUtil;
import com.shuiyujie.generator.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by shui 2017/8/21
 */
public class PbTransferTask extends InitTask {

    private static String FILE_PATH = MyConfiguration.getString("pbTransferSavePath");

    private String TASK_FTL_NAME = "pbtransfer.ftl";

    public boolean doInternale() throws Exception {

        // 指定模板文件
        Template template = super.configuration.getTemplate(TASK_FTL_NAME);

        // 创建数据模型
        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");
        Map<String, Object> root = new HashMap<>();
        List<ColumnInfo> classColumnInfos = this.getInstance();
        root.put("columnList", classColumnInfos);
        root.put("paramName", tableInfo.getName().toUpperCase() + "_FIELDS");

        String filePathName = FILE_PATH + "/" + "PbTransfer.java";

        FileUtil.createNewFile(FILE_PATH,filePathName);

        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePathName), "utf-8"));
        template.process(root, out);
        out.flush();
        out.close();

        return false;
    }

    private List<ColumnInfo> getInstance() {
        List<ColumnInfo> classColumnInfos = (List<ColumnInfo>) contexts.get("classColumns");
        for (ColumnInfo classColumnInfo : classColumnInfos) {
            classColumnInfo.setName(StringUtil.upcaseFirst(classColumnInfo.getName()));
        }
        return classColumnInfos;
    }

    public static void main(String[] args) throws Exception {
        new PbTransferTask().doInternale();
    }

}
