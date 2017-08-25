package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.Protobuf;
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
public class PbTask extends InitTask {

    private static String PACKAGE_NAME = MyConfiguration.getString("pbPackage");

    private static String FILE_PATH = MyConfiguration.getString("pbSavePath");

    private static String TASK_FTL_NAME = "protobuf.ftl";

    public boolean doInternale() throws Exception {

        // 指定模板文件
        Template template = super.configuration.getTemplate(TASK_FTL_NAME);

        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        Protobuf pb = this.getInstance();
        root.put("protobuf", pb);

        String filePathName = FILE_PATH + "/" + className + ".proto";

        FileUtil.createNewFile(FILE_PATH, filePathName);

        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePathName), "utf-8"));
        template.process(root, out);

        out.flush();
        out.close();

        return false;
    }

    private Protobuf getInstance() {

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");
        List<ColumnInfo> pbColumns = (List<ColumnInfo>) contexts.get("pbColumns");

        Protobuf pb = new Protobuf();

        pb.setPackageName(PACKAGE_NAME);
        pb.setName(StringUtil.dbColumn2ClassColumn(tableInfo.getName()));
        pb.setClassColumnList(pbColumns);

        return pb;

    }

    // protoc cmtComment.proto --java_out="/Users/shui/company/xchat-core/source/xchat-protobuf/src/main/java/com/x16/xchat/protobuf/proto/org"
    private void createPbProto() {
        String shpath = FILE_PATH;   //程序路径

        Process process = null;

        try {
            String command1 = "cd" + " " + FILE_PATH;
            process = Runtime.getRuntime().exec(command1);
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
//        new PbTask().doInternale();
//        String command = "protoc" + " " + className + ".proto"+ " " + "--java_out=" + "\"" + FILE_PATH + "\"";
//        System.out.println(command);
        new PbTask().createPbProto();
    }
}
