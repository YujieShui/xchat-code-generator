package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.model.VO;
import com.shuiyujie.generator.utils.Constants;
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
 * created by shui 2017/8/20
 */
public class VOTask extends InitTask {

    private static String PACKAGE_NAME = "com.x16.xchat.persist";

    private static String SUPERCLASS_NAME = "XcVO";

    private static String TASK_FTL_NAME = "vo.ftl";

    private static String FILE_PATH = "/Users/shui/workspace/code/UserVO.java";

    public boolean doInternale() throws Exception{

        Configuration configuration = new Configuration();

        try {
            // 指定数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 指定模板文件
            Template template = configuration.getTemplate(TASK_FTL_NAME);

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            VO vo = this.getInstance();
            root.put("vo",vo);

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(FILE_PATH), "utf-8"));
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

    private VO getInstance (){

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");

        VO vo = new VO();

        vo.setPackageName(PACKAGE_NAME);
        vo.setClassName(StringUtil.tableName2ClassName(tableInfo.getName()));
        vo.setSuperclass(SUPERCLASS_NAME);
        List<ColumnInfo> classColumns= (List<ColumnInfo>) contexts.get("classColumns");
        vo.setClassColumnList(classColumns);

        return vo;

    }

    public static void main(String[] args) throws Exception {

        new VOTask().doInternale();
    }
}
