package com.shuiyujie.generator.task;

import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.Mapper;
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
 * created by shui 2017/8/21
 */
public class MapperTask extends InitTask {

    private static String TASK_FTL_NAME = "mapper.ftl";

    public boolean doInternale() throws Exception {


        Configuration configuration = new Configuration();

        try {
            // 指定数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 指定模板文件
            Template template = configuration.getTemplate(TASK_FTL_NAME);

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            Mapper mapper = this.getMapper();
            root.put("mapper", mapper);
            root.put("resultMap",mapper.getResultMap());

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/shui/workspace/code/User.xml"), "utf-8"));
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

    private Mapper getMapper() {

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");
        List<ColumnInfo> classColumns = (List<ColumnInfo>) contexts.get("classColumns");

        Mapper mapper = new Mapper();
        String tableName = tableInfo.getName();

        mapper.setTableName(tableName);
        mapper.setNamespace(StringUtil.tableName2ClassName(tableName));
        mapper.setName(StringUtil.dbColumn2ClassColumn(tableName));
        mapper.setClassColumnList(classColumns);
        mapper.setDbColumnList(tableInfo.getDbColumnList());
        Map<String, String> resultMap = this.getResultMap();
        mapper.setResultMap(resultMap);

        return mapper;
    }

    /**
     * 组合 mapper.xml 中的 ResultMap
     * key:java属性名
     * value:数据库字段名
     *
     * @return
     */
    private Map<String, String> getResultMap() {

        Map<String, String> resultMap = new HashMap<>();
        List<ColumnInfo> dbColumnInfos = (List<ColumnInfo>) contexts.get("dbColumns");
        for (ColumnInfo columnInfo : dbColumnInfos) {
            String value = columnInfo.getName();
            String key = StringUtil.dbColumn2ClassColumn(value);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    public static void main(String[] args) throws Exception{
        new MapperTask().doInternale();
    }
}
