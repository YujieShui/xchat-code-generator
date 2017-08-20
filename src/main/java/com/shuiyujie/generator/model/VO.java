package com.shuiyujie.generator.model;

import com.shuiyujie.generator.test.Property;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * VO 实体类
 * created by shui 2017/8/19
 */
public class VO extends TableInfo {

    private String packageName; // 实体所在的包名

    private String className; // 实体类名

    private String superclass; // 父类名

    /*
     * 将TableInfor中的字段名称格式化后存储
     * eg： org_id --> orgId
     *      varchar --> String
      */
    private List<ColumnInfo> columnList;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSuperclass() {
        return superclass;
    }

    public void setSuperclass(String superclass) {
        this.superclass = superclass;
    }

    @Override
    public List<ColumnInfo> getColumnList() {
        return columnList;
    }

    @Override
    public void setColumnList(List<ColumnInfo> columnList) {
        this.columnList = columnList;
    }
}