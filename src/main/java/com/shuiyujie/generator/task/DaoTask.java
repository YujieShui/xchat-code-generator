package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.IDaoModel;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.utils.Constants;
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

    private static String TASK_FTL_NAME = "mapper.ftl";

    private String FTL_NAME = "iservice.ftl";

    private String PACKAGE_NAME = "com.x16.xchat.app.service";

    public boolean doInternale() throws Exception {

        try {
            // 指定模板文件
            Template template = super.configuration.getTemplate(TASK_FTL_NAME);

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            IDaoModel model = this.getInstance();
            root.put("idao", model);

            String filePathName = FILE_PATH + "/" +className + ".xml";

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/shui/workspace/code/IUserService.java"), "utf-8"));
            template.process(root, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

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
