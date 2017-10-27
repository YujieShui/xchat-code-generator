package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.Protobuf;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.source.MyConfiguration;
import com.shuiyujie.generator.utils.FileUtil;
import com.shuiyujie.generator.utils.StringUtil;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by shui 2017/8/21
 */
public class PbTask extends InitTask {

    Map<String, Object> params = new HashMap<>();

    private static String PACKAGE_NAME = MyConfiguration.getString("pbPackage");

    private static String FILE_PATH = MyConfiguration.getString("pbSavePath");

    private static String TASK_FTL_NAME = "protobuf.ftl";

    public boolean doInternale() throws Exception {

        // 指定模板文件
        Template template = InitTask.configuration.getTemplate(TASK_FTL_NAME);

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

        this.createPbProto(className + ".proto");

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

    /**
     * 生成一个 shell 文件
     * 通过 shell 文件编译生成 ProtoBuf 文件
     *
     * @param fileName
     */
    private void createPbProto(String fileName) {

        String shell = "#!/bin/bash\n";

        String one = "cd " + FILE_PATH + "\n";

        String two = "protoc " + fileName + " --java_out=\"" + FILE_PATH + "\"\n";

        shell += one;
        shell += two;

        String filePath = FILE_PATH + "/test.sh";
        FileUtil.writeToFile(filePath,shell,"UTF-8");
        System.out.println(filePath);

        // 创建 shell 脚本文件
        if(FileUtil.writeToFile(filePath,shell,"UTF-8")){
            // 执行 shell 文件
            Runtime runTime = Runtime.getRuntime();
            String cmd = "bash " + filePath;

            try {
                Process process = runTime.exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws Exception {
        new PbTask().doInternale();
//        new PbTask().createPbProto();
//        String command = "protoc" + " " + className + ".proto"+ " " + "--java_out=" + "\"" + FILE_PATH + "\"";
//        System.out.println(command);
//        new PbTask().createPbProto();
    }
}
