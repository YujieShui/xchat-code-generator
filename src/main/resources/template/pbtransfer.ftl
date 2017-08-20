public static final String[] ORG_CUSTOMER_FIELDS = new String[]{

<#list vo.columnList as column>
    "${column.name}",
    // 修改成最后一个没有逗号
</#list>

};