<#assign idLength=0/>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperFullName}">

    <#list columnList as columnMap>
        <#if columnMap.isId=='true'>
            <#assign idLength = idLength + 1/>
        </#if>
    </#list>

    <#assign columnStrList><#list columnList as columnMap>a.${columnMap.col} as ${StringUtils.underlineToCamelCase(columnMap.col)},</#list></#assign>
    <#assign columnStrListNoAs><#list columnList as columnMap>${columnMap.col},</#list></#assign>

    <sql id="Base_Column_List">
        <#if columnStrList?? && (columnStrList?length>0)>
            ${columnStrList?substring(0,(columnStrList?length)-1)}
        <#else>
            *
        </#if>
    </sql>

    <sql id="Base_select_table">
        ${tableName} a
    </sql>


    <#if (idLength>0)>
    <select id="selectByPrimaryKey" resultType="${entityPackage}.${entityName}">
        select
        <include refid="Base_Column_List"/>
        from <include refid="Base_select_table"/>
        <where>
        <#list columnList as columnMap>
            <#if columnMap.isId=='true'>
              and  a.${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}}
            </#if>
        </#list>
        </where>
    </select>
    </#if>

    <select id="selectBySelective" resultType="${entityPackage}.${entityName}">
        select
        <include refid="Base_Column_List"/>
        from <include refid="Base_select_table"/>
        <where>
           <#list columnList as columnMap>
               <if test="${StringUtils.underlineToCamelCase(columnMap.col)} != null<#if (StringUtils.equals(columnMap.javaType,"String"))> and ${StringUtils.underlineToCamelCase(columnMap.col)} != ''</#if>">
                   and a.${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}}
               </if>
           </#list>
        </where>
    </select>


    <#assign insertStrList>
        <#list columnList as columnMap>
            ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}},
        </#list>
    </#assign>
    <#assign insertStrList="${StringUtils.trimToEmpty(insertStrList)}"/>

    <insert id="insert" parameterType="${entityPackage}.${entityName}">
        insert into ${tableName} (
            ${columnStrListNoAs?substring(0,columnStrListNoAs?length-1)}
        )
        values (
            ${insertStrList?substring(0,insertStrList?length-1)}
        )
    </insert>


    <insert id="insertSelective" parameterType="${entityPackage}.${entityName}">

        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
                <#list columnList as columnMap>
                    <if test="${StringUtils.underlineToCamelCase(columnMap.col)} != null">
                        ${columnMap.col},
                    </if>
                </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
               <#list columnList as columnMap>
                   <if test="${StringUtils.underlineToCamelCase(columnMap.col)} != null">
                       ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}},
                   </if>
               </#list>
        </trim>
    </insert>

        <#if (idLength>0 && (idLength<columnList.size()))>
    <update id="updateByPrimaryKeySelective" parameterType="${entityPackage}.${entityName}">
        update ${tableName}
        <set>
            <#list columnList as columnMap>
               <#if columnMap.isId=='false'>
                 <if test="${StringUtils.underlineToCamelCase(columnMap.col)} != null">
                     ${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}},
                 </if>
               </#if>
            </#list>
        </set>

        <where>
           <#list columnList as columnMap>
              <#if columnMap.isId=='true'>
              and  ${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}}
              </#if>
           </#list>
        </where>
    </update>
        </#if>

<#if (idLength>0 && (idLength<columnList.size()))>
    <update id="updateByPrimaryKey" parameterType="${entityPackage}.${entityName}">
        update ${tableName}
        <set>
            <#list columnList as columnMap>
               <#if columnMap.isId=='false'>
                   ${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}},
               </#if>
            </#list>
        </set>

        <where>
           <#list columnList as columnMap>
              <#if columnMap.isId=='true'>
              and  ${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}}
              </#if>
           </#list>
        </where>
    </update>
</#if>

    <delete id="deleteByPrimaryKey">
        delete from ${tableName}
        <where>
        <#list columnList as columnMap>
            <#if columnMap.isId=='true'>
              and  ${columnMap.col} = ${r'#{'}${StringUtils.underlineToCamelCase(columnMap.col)},jdbcType=${columnMap.jdbcType}}
            </#if>
        </#list>
        </where>
    </delete>

</mapper>