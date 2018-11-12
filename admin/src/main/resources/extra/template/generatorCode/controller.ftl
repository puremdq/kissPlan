package ${controllerPackage};

<#if StringUtils.isNotBlank(baseControllerFullName)>
import ${baseControllerFullName};
import ${serviceFullName};
</#if>

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/${entityName?uncap_first}")
public class ${controllerName} <#if StringUtils.isNotBlank(baseControllerFullName)>extends ${StringUtils.substringAfterLast(baseControllerFullName,".")}<${StringUtils.substringAfterLast(serviceFullName,".")}></#if> {


}
