package com.shuiyujie.generator.model;

/**
 * VO 实体类
 * created by shui 2017/8/19
 */
public class VO extends TableInfo {

    private String className; // 实体类名

    private String superclass; // 父类名

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

}
