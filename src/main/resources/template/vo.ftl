package ${vo.packageName};

import com.x16.xchat.persist.XcVO;
/**
* VO 实体类
* created by shui
*/
public class ${vo.className} extends XcVO{

    <#list vo.columnList as vo>
    private ${vo.type} ${vo.name};

    </#list>

    <#list vo.columnList as column>
    public ${column.type} get${column.name?cap_first}() {
        return ${column.name};
    }

    public void set ${column.name?cap_first}(${column.type} ${column.name?cap_first}()){
        this.${column.name?cap_first} = ${column.name?cap_first};
    }

    </#list>
}