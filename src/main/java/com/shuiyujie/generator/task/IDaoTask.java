package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.IDaoModel;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.model.VO;
import com.shuiyujie.generator.source.MyConfiguration;
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
public class IDaoTask extends InitTask {

    private static String PACKAGE_NAME = MyConfiguration.getString("serviceInterfacePackage");

    private static String FILE_PATH = MyConfiguration.getString("serviceInterfaceSavePath");

    private String FTL_NAME = "iservice.ftl";

    public boolean doInternale() throws Exception {


        Configuration configuration = new Configuration();

        try {
            // 指定数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 指定模板文件
            Template template = configuration.getTemplate(FTL_NAME);

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            IDaoModel model = this.getInstance();
            root.put("idao", model);

            String filePathName = FILE_PATH + "/" + "I" + className + "Service.java";

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePathName), "utf-8"));
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
        new IDaoTask().doInternale();
    }

}
