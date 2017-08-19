package com.shuiyujie.generator.task;


import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.utils.Constants;

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
public class InitTask {

    Connection conn = null;
    ResultSet tableSet = null;// 表信息
    ResultSet columnSet = null;// 字段信息

    protected boolean doInternale() {

        try {
            conn = getConnection();
            /*
             * 获取数据库源数据
             * http://blog.csdn.net/chen_zw/article/details/18816599
             */
            DatabaseMetaData dbMetaData = conn.getMetaData();

            // 获取数据库表结果集
            tableSet = dbMetaData.getTables(null, "root", Constants.ENTITY_SUFFIX, new String[]{"TABLE"});

            // 遍历
            Map<String, TableInfo> tableInfoMap = new HashMap<>();
            while (tableSet.next()) {

                // 获取表名
                String tableName = tableSet.getString("TABLE_NAME").toLowerCase();

                TableInfo tableInfo = new TableInfo();
                tableInfo.setName(tableName);

                // 表注释
                String tableRemark = tableSet.getString("REMARKS");
                tableInfo.setRemark(tableRemark);

                // 表类型
                String tableType = tableSet.getString("TABLE_TYPE");
                tableInfo.setType(tableType);


                System.out.println("tableNamez:" + tableName);
                System.out.println("tableRemark:" + tableRemark);
                System.out.println("tableType:" + tableType);

                // 获取表字段结果集
                columnSet = dbMetaData.getColumns(null,"root",tableName,Constants.ENTITY_SUFFIX);
                List<ColumnInfo> columnInfos = new ArrayList<>();
                while (columnSet.next()){
                    String columnName = columnSet.getString("COLUMN_NAME").toLowerCase();
                    String columnType = columnSet.getString("TYPE_NAME").toLowerCase();
                    String columnRemark = columnSet.getString("REMARKS").toLowerCase();

                    ColumnInfo ci = new ColumnInfo();
                    ci.setName(columnName);
                    ci.setType(columnType);
                    ci.setRemark(columnRemark);

                    columnInfos.add(ci);
                }
                tableInfo.setColumnList(columnInfos);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        new InitTask().doInternale();
    }

}
