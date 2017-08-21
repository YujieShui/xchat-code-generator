public static final String[] ${paramName} = new String[]{

<#list columnList as column>
    <#if (column_has_next)>"${column.name}",
    <#else>"${column.name}"
    </#if>
</#list>
};