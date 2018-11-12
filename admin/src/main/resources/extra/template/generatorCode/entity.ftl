<#assign idNum=0/>
<#assign idType="INPUT"/>
<#list columnList as columnMap>
    <#if columnMap.isId=='true'>
        <#assign idNum=idNum+1/>
    </#if>
</#list>
package ${entityPackage};

<#if StringUtils.isNotBlank(baseEntityFullName)>
import ${baseEntityFullName};
</#if>
import lombok.Data;
<#if (idNum>0)>
import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;
</#if>

@Data
public class ${entityName} <#if StringUtils.isNotBlank(baseEntityFullName)>extends ${StringUtils.substringAfterLast(baseEntityFullName,".")}</#if> {

    private static final long serialVersionUID = 1L;

    public ${entityName}() {
    }
<#if (idNum>0)>
    <#assign idAndTypeStrList><#list columnList as columnMap> <#if columnMap.isId=='true'>${columnMap.javaType} ${StringUtils.underlineToCamelCase(columnMap.col)},</#if></#list></#assign>
    <#assign idAndTypeStrList="${StringUtils.trimToEmpty(idAndTypeStrList)}"/>
    <#assign constructorStr>
        <#list columnList as columnMap>
            <#if columnMap.isId=='true'>
        this.${StringUtils.underlineToCamelCase(columnMap.col)}=${StringUtils.underlineToCamelCase(columnMap.col)};
            </#if>
        </#list>
    </#assign>
    <#assign constructorStr="${StringUtils.trimToEmpty(constructorStr)}"/>
    public ${entityName}(${idAndTypeStrList?substring(0,(idAndTypeStrList?length)-1)}) {
    ${StringUtils.trimToEmpty(constructorStr)}
    }
</#if>

<#list columnList as columnMap>
    /**
     * ${columnMap.remarks}
     * 表字段： ${tableName}.${columnMap.col}
     */
    <#if columnMap.isId=='true'>
    <#if (idNum==1)>
        <#if (columnMap.javaType=='Integer')>
            <#assign idType="AUTO"/>
        <#else>
            <#assign idType="UUID"/>
        </#if>
    </#if>
    @TableId(type = IdType.${idType}, value = "${columnMap.col}")
    </#if>
    private ${columnMap.javaType} ${StringUtils.underlineToCamelCase(columnMap.col)};
</#list>

}