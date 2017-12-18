package ${idao.packageName};

import java.util.List;
import java.util.Map;

public interface I${idao.className}Service{

    void insert${idao.className}(${idao.className}VO ${idao.className?uncap_first}VO);

    void update${idao.className}(${idao.className}VO ${idao.className?uncap_first}VO);

    void delete${idao.className}(${idao.className}VO ${idao.className?uncap_first}VO);

    List<${idao.className}VO> load${idao.className}(Map<String, Object> params);

}