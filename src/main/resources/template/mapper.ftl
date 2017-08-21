<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${mapper.namespace}">

    <resultMap id="${mapper.name}Map" type="${mapper.name}VO">
        <#list resultMap?keys as itemKey>
            <result property="${itemKey}" column="${resultMap[itemKey]}"/>
        </#list>
    </resultMap>

    <select id="load${mapper.namespace}" resultMap="${mapper.name}Map">
        SELECT * FROM ${mapper.tableName}
        <where>
        <#list resultMap?keys as itemKey>
            <if test="${itemKey}!=null">
                and ${resultMap[itemKey]} = ${itemKey}
            </if>
        </#list>
        </where>
    </select>

    <!-- 单个插入 -->
    <insert id="insert${mapper.namespace}" parameterType="${mapper.name}VO">
        INSERT INTO ${mapper.tableName} (
        <#list mapper.dbColumnList as dbColumn>
            <#if (dbColumn_has_next)>${dbColumn.name},
            <#else>${dbColumn.name}
            </#if>
        </#list>
        )
        VALUES(
        <#list mapper.classColumnList as classColumn>
            <#if (classColumn_has_next)>${classColumn.name},
            <#else>${classColumn.name}
            </#if>
        </#list>
        )
    </insert>

    <!-- 单个更新 -->
    <update id="update${mapper.namespace}" parameterType="${mapper.name}VO">
        UPDATE ${mapper.tableName}
        <set>
            <#list resultMap?keys as itemKey>
                <if test="${itemKey}!=null">
                ${resultMap[itemKey]}= ${itemKey},
                </if>
            </#list>
        </set>
        where id=${r'#{id}'}
    </update>

    <!-- 删除 -->
    <update id="delete${mapper.namespace}" parameterType="${mapper.name}VO" >
        update ${mapper.tableName}
        set
        data_status=0,
        sync_key=${r'#{syncKey}'},
        modified_by = ${r'#{modifiedBy}'},
        modified_date = ${r'#{modifiedDate}'}
        where
        id = ${r'#{id}'}
    </update>

</mapper>