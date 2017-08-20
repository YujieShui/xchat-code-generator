package com.shuiyujie.generator.model;

import java.util.List;

/**
 * 表信息
 * created by shui 2017/8/19
 */
public class TableInfo {

    private String name;
    private String type;
    private String remark;
    private List<ColumnInfo> dbColumnList;
    private List<ColumnInfo> classColumnList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ColumnInfo> getDbColumnList() {
        return dbColumnList;
    }

    public void setDbColumnList(List<ColumnInfo> dbColumnList) {
        this.dbColumnList = dbColumnList;
    }

    public List<ColumnInfo> getClassColumnList() {
        return classColumnList;
    }

    public void setClassColumnList(List<ColumnInfo> classColumnList) {
        this.classColumnList = classColumnList;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", dbColumnList=" + dbColumnList +
                ", classColumnList=" + classColumnList +
                '}';
    }
}
