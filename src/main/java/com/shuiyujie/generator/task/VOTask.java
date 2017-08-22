package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.model.VO;
import com.shuiyujie.generator.source.MyConfiguration;
import com.shuiyujie.generator.utils.FileUtil;
import com.shuiyujie.generator.utils.StringUtil;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by shui 2017/8/20
 */
public class VOTask extends InitTask {

    private static String PACKAGE_NAME = MyConfiguration.getString("voPackage");

    private static String FILE_PATH = MyConfiguration.getString("voSavePath");

    private static String SUPERCLASS_NAME = "XcVO";

    private static String TASK_FTL_NAME = "vo.ftl";

    public boolean doInternale() throws Exception {

        // 指定模板文件
        Template template = super.configuration.getTemplate(TASK_FTL_NAME);

        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        VO vo = this.getInstance();
        root.put("vo", vo);

        String filePathName = FILE_PATH + "/" + className + "VO.java";

        FileUtil.createNewFile(FILE_PATH,filePathName);

        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePathName), "utf-8"));
        template.process(root, out);
        out.flush();
        out.close();

        return false;
    }

    private VO getInstance() {

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");

        VO vo = new VO();

        vo.setPackageName(PACKAGE_NAME);
        vo.setClassName(StringUtil.tableName2ClassName(tableInfo.getName()));
        vo.setSuperclass(SUPERCLASS_NAME);
        List<ColumnInfo> classColumns = (List<ColumnInfo>) contexts.get("classColumns");

        List<String> xcVOColumns = getSupperColumn();
        List<ColumnInfo> co = new ArrayList<>();
        for (ColumnInfo classColumn : classColumns) {
            if(xcVOColumns.contains(classColumn.getName())){
                continue;
            }

            co.add(classColumn);
        }

        vo.setClassColumnList(co);

        return vo;

    }

    private List<String> getSupperColumn(){

        List<String> xcVOColumns = new ArrayList<>();
        xcVOColumns.add("id");
        xcVOColumns.add("dataStatus");
        xcVOColumns.add("createdDate");
        xcVOColumns.add("modifiedDate");
        xcVOColumns.add("createdBy");
        xcVOColumns.add("createdName");
        xcVOColumns.add("modifiedBy");
        xcVOColumns.add("modifiedName");
        xcVOColumns.add("syncKey");

        return xcVOColumns;
    }

    public static void main(String[] args) throws Exception {

        new VOTask().doInternale();
    }
}
