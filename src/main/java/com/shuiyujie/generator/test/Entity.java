package com.shuiyujie.generator.test;

import java.util.List;

/**
 * 实体类
 * created by shui 2017/8/19
 */
public class Entity {

    private String javaPackage; // 实体所在的包名

    private String className; // 实体类名

    private String superclass; // 父类名

    List<Property> properties;// 属性集合

    private boolean constructors;// 是否有构造函数

    public String getJavaPackage() {
        return javaPackage;
    }

    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public boolean isConstructors() {
        return constructors;
    }

    public void setConstructors(boolean constructors) {
        this.constructors = constructors;
    }
}
