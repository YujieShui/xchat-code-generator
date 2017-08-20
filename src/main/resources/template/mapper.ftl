<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">
    <resultMap id="BaseResultMap" type="${entityType}">
        <id column="ID" jdbcType="BIGINT" property="id" />
    ${resultMap}
    </resultMap>

    <!-- 单个插入 -->
    <insert id="insert${entityName}" parameterType="${entityType}" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        ${insertIfColumns}
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        ${insertIfProps}
        </trim>
    </insert>

    <!-- 单个更新 -->
    <update id="update${entityName}" parameterType="${entityType}">
        update ${tableName}
        <set>
        ${updateColProps}
        </set>
        where ID = <#noparse>#{id,jdbcType=BIGINT}</#noparse>
    </update>

    <!-- 删除 -->
    <update id="delete${entityName}" parameterType="${entityType}" >
        update ${tableName} set UPDATED = <#noparse>#{updated,jdbcType=TIMESTAMP}</#noparse>,UPDATEDBY = <#noparse>#{updatedby,jdbcType=VARCHAR}</#noparse>, IS_DELETE = 'Y'
        where ID = <#noparse>#{id,jdbcType=BIGINT}</#noparse>
    </update>

    <!-- 单个查询 -->
    <select id="get${entityName}" parameterType="${entityType}" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${tableName}
        WHERE IS_DELETE = 'N'
        <if test="id != null">
            AND id = <#noparse>#{id, jdbcType=BIGINT}</#noparse>
        </if>
    </select>
</mapper>