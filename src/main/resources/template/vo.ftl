package ${vo.packageName};

import com.x16.xchat.persist.XcVO;
/**
* VO 实体类
* created by shui
*/
public class ${vo.className}VO <#if vo.superclass?has_content> extends ${vo.superclass} </#if>{

    <#list vo.classColumnList as column>
    private ${column.type} ${column.name};

    </#list>

    <#list vo.classColumnList as column>
    public ${column.type} get${column.name?cap_first}() {
        return ${column.name};
    }

    public void set${column.name?cap_first}(${column.type} ${column.name?cap_first}){
        this.${column.name} = ${column.name};
    }

    </#list>
}