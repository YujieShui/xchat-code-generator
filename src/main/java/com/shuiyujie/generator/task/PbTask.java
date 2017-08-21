package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.Protobuf;
import com.shuiyujie.generator.model.TableInfo;
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
 * created by shui 2017/8/21
 */
public class PbTask extends InitTask{

    private static String PB_PACKAGE_NAME = "com.x16.xchat.protobuf.proto";

    private static String TASK_FTL_NAME = "protobuf.ftl";

    public boolean doInternale() throws Exception{

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");

        Configuration configuration = new Configuration();

        try {
            // 指定数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 指定模板文件
            Template template = configuration.getTemplate(TASK_FTL_NAME);

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            Protobuf pb = this.tableInfo2Pb(tableInfo);
            root.put("protobuf",pb);

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/shui/workspace/code/User.proto"), "utf-8"));
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

    private Protobuf tableInfo2Pb(TableInfo tableInfo){

        Protobuf pb = new Protobuf();

        pb.setPackageName(PB_PACKAGE_NAME);
        pb.setName(StringUtil.dbColumn2ClassColumn(tableInfo.getName()));
        List<ColumnInfo> pbColumns= (List<ColumnInfo>) contexts.get("pbColumns");
        pb.setClassColumnList(pbColumns);

        return pb;

    }

    public static void main(String[] args) throws Exception{
        new PbTask().doInternale();
    }
}
