package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.IDaoModel;
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
import java.util.Map;

/**
 * created by shui 2017/8/21
 */
public class DaoTask extends InitTask {

    private static String PACKAGE_NAME = MyConfiguration.getString("servicePackage");

    private static String FILE_PATH = MyConfiguration.getString("serviceSavePath");

    private static String TASK_FTL_NAME = "service.ftl";

    public boolean doInternale() throws Exception {

        // 指定模板文件
        Template template = super.configuration.getTemplate(TASK_FTL_NAME);

        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        IDaoModel model = this.getInstance();
        root.put("idao", model);

        String filePathName = FILE_PATH + "/" + className + "Service.java";

        FileUtil.createNewFile(FILE_PATH, filePathName);

        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePathName), "utf-8"));
        template.process(root, out);
        out.flush();
        out.close();

        return false;
    }

    private IDaoModel getInstance() {

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");

        IDaoModel model = new IDaoModel();
        model.setPackageName(PACKAGE_NAME);
        model.setClassName(StringUtil.tableName2ClassName(tableInfo.getName()));

        return model;
    }

    public static void main(String[] args) throws Exception {
        new DaoTask().doInternale();
    }

}
