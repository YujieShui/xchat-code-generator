package com.shuiyujie.generator.model;

import com.shuiyujie.generator.model.TableInfo;

import java.util.Map;

/**
 * created by shui 2017/8/21
 */
public class Mapper extends TableInfo{

    private String tableName; // 数据库名称

    private String namespace;

    private Map<String,String> resultMap;// key:java字段 | value:数据库字段

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }
}
