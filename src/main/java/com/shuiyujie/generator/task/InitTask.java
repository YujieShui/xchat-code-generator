package com.shuiyujie.generator.task;


import com.shuiyujie.generator.application.ApplicationContext;
import com.shuiyujie.generator.application.ApplicationTask;
import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.utils.Constants;
import com.shuiyujie.generator.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shuiyujie.generator.utils.DBUtil.getConnection;

/**
 * created by shui 2017/8/19
 */
public class InitTask extends ApplicationTask{

    Map<String,Object> contexts = new HashMap<>();

    public static Configuration configuration;

    public static String className;

    Connection conn = null;
    ResultSet tableSet = null;// 表信息
    ResultSet columnSet = null;// 字段信息

    public InitTask(){
        this.init();
        this.initConfiguration();
    }

    protected boolean init() {

        try {
            conn = getConnection();
            /*
             * 获取数据库源数据
             * http://blog.csdn.net/chen_zw/article/details/18816599
             */
            DatabaseMetaData dbMetaData = conn.getMetaData();

            // 获取数据库表结果集
            tableSet = dbMetaData.getTables(null, null,"%", null);

            // 遍历
            Map<String, TableInfo> tableInfoMap = new HashMap<>();
            while (tableSet.next()) {

                // 获取表名
                String tableName = tableSet.getString("TABLE_NAME").toLowerCase();
                className = StringUtil.tableName2ClassName(tableName);

                TableInfo tableInfo = new TableInfo();
                tableInfo.setName(tableName);

                // 表注释
                String tableRemark = tableSet.getString("REMARKS");
                tableInfo.setRemark(tableRemark);

                // 表类型
                String tableType = tableSet.getString("TABLE_TYPE");
                tableInfo.setType(tableType);

                // 获取表字段结果集
                columnSet = dbMetaData.getColumns(null,null,tableName,null);
                List<ColumnInfo> columnInfos = new ArrayList<>();
                while (columnSet.next()){
                    String columnName = columnSet.getString("COLUMN_NAME").toLowerCase();
                    String columnType = columnSet.getString("TYPE_NAME").toLowerCase();
                    String columnRemark = columnSet.getString("REMARKS").toLowerCase();

                    ColumnInfo ci = new ColumnInfo();
                    ci.setName(columnName);
                    ci.setType(columnType);
                    ci.setRemark(columnRemark);

                    System.out.println(ci.getName());
                    System.out.println(ci.getType());
                    System.out.println(ci.getRemark());

                    columnInfos.add(ci);
                }
                tableInfo.setDbColumnList(columnInfos);
                this.dbColumn2classColumn(columnInfos);
                this.dbColumn2PbColumn(columnInfos);

                // 表信息放入上下文中
                contexts.put("dbColumns",columnInfos);
                contexts.put("tableInfo",tableInfo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 初始化 FreeMraker Configuration
     */
    private void initConfiguration (){

        this.configuration = new Configuration();
        try {
            // 指定数据源
            this.configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            this.configuration.setObjectWrapper(new DefaultObjectWrapper());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库表字段转换成驼峰法命名Java变量名
     * @param columnInfos 数据库表字段 user_id
     */
    private void dbColumn2classColumn(List<ColumnInfo > columnInfos){

        // 驼峰法命名字段
        List<ColumnInfo> classColumns = new ArrayList<>();

        for (ColumnInfo columnInfo : columnInfos) {
            ColumnInfo classColumn = new ColumnInfo();
            classColumn.setName(StringUtil.dbColumn2ClassColumn(columnInfo.getName()));
            classColumn.setType(StringUtil.typeTransfer(columnInfo.getType()));// type 需要转换
            classColumn.setRemark(columnInfo.getRemark());
            classColumn.setLen(columnInfo.getLen());
            classColumn.setPrecision(classColumn.getPrecision());

            classColumns.add(classColumn);
        }

        contexts.put("classColumns",classColumns);
    }

    /**
     * 数据库字段转换成pb中类型
     * @param columnInfos
     */
    private void dbColumn2PbColumn(List<ColumnInfo > columnInfos){

        List<ColumnInfo> pbColumns = new ArrayList<>();

        for (ColumnInfo columnInfo : columnInfos) {
            ColumnInfo classColumn = new ColumnInfo();
            classColumn.setName(StringUtil.dbColumn2ClassColumn(columnInfo.getName()));
            classColumn.setType(StringUtil.typeTransfer4Pb(columnInfo.getType()));// type 需要转换
            classColumn.setRemark(columnInfo.getRemark());
            classColumn.setLen(columnInfo.getLen());
            classColumn.setPrecision(classColumn.getPrecision());

            pbColumns.add(classColumn);
        }

        contexts.put("pbColumns",pbColumns);
    }

}
