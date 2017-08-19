package com.shuiyujie.generator.test;

/**
 * 实体对应的属性类
 * created by shui 2017/8/19
 */
public class Property {
    private String javaType; // 属性数据类型

    private String propertyName; // 属性名称

    private PropertyType propertyType;

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
}

