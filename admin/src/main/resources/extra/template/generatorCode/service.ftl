<#assign hasId=0/>
<#assign idAndTypeStrList><#list columnList as columnMap> <#if columnMap.isId=='true'>${columnMap.javaType} ${StringUtils.underlineToCamelCase(columnMap.col)},<#assign hasId=1/></#if></#list></#assign>
<#assign idAndTypeStrList="${StringUtils.trimToEmpty(idAndTypeStrList)}"/>
<#assign idStrList><#list columnList as columnMap> <#if columnMap.isId=='true'>${StringUtils.underlineToCamelCase(columnMap.col)},</#if></#list></#assign>
<#assign idStrList="${StringUtils.trimToEmpty(idStrList)}"/>
package ${servicePackage};

import ${entityFullName};
<#if StringUtils.isNotBlank(baseServiceFullName)>
import ${baseServiceFullName};
import ${mapperFullName};
</#if>

import org.springframework.stereotype.Service;

@Service
public class ${serviceName} extends <#if StringUtils.isNotBlank(baseServiceFullName)>${StringUtils.substringAfterLast(baseServiceFullName,".")}<${StringUtils.substringAfterLast(entityFullName,".")}, ${StringUtils.substringAfterLast(mapperFullName,".")}></#if> {

    public ${StringUtils.substringAfterLast(entityFullName,".")} get(${idAndTypeStrList?substring(0,(idAndTypeStrList?length)-1)}) {
        return this.mapper.selectByPrimaryKey(${idStrList?substring(0,(idStrList?length)-1)});
    }

    public boolean delete(${idAndTypeStrList?substring(0,(idAndTypeStrList?length)-1)}) {
        return this.mapper.deleteByPrimaryKey(${idStrList?substring(0,(idStrList?length)-1)}) > 0;
    }
}
