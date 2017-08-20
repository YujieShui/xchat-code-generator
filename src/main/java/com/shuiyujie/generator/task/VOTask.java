package com.shuiyujie.generator.task;

import com.shuiyujie.generator.application.ApplicationContext;
import com.shuiyujie.generator.application.ApplicationTask;
import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.model.VO;
import com.shuiyujie.generator.test.Entity;
import com.shuiyujie.generator.test.Property;
import com.shuiyujie.generator.test.PropertyType;
import com.shuiyujie.generator.utils.Constants;
import com.shuiyujie.generator.utils.FileUtil;
import com.shuiyujie.generator.utils.StringUtil;
import com.sun.tools.jdi.VoidValueImpl;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.tools.jconsole.Tab;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by shui 2017/8/20
 */
public class VOTask extends InitTask {

    private static String VO_PACKAGE_NAME = "com.x16.xchat.persist.org";

    private static String VO_SUPERCLASS_NAME = "XcVO";

    public boolean doInternale() throws Exception{

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");

        Configuration configuration = new Configuration();

        try {
            // 指定数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 指定模板文件
            Template template = configuration.getTemplate("vo.ftl");

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            VO vo = this.tableInfo2VO(tableInfo);
            root.put("vo",vo);

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/shui/workspace/code/user.java"), "utf-8"));
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

    public VO tableInfo2VO (TableInfo tableInfo){

        VO vo = new VO();

        vo.setPackageName(VO_PACKAGE_NAME);
        vo.setClassName(StringUtil.tableName2ClassName(tableInfo.getName()));
        vo.setSuperclass(VO_SUPERCLASS_NAME);
        List<ColumnInfo> classColumns= (List<ColumnInfo>) contexts.get("classColumns");
        vo.setClassColumnList(classColumns);

        return vo;

    }

    public static void main(String[] args) throws Exception {

        new VOTask().doInternale();
    }
}
