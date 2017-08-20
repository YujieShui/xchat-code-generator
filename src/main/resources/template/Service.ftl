package ${packageStr};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${className} implements xxx {

    void insert${entityName}(${entityClassName} entity){
        daoRouter.insert("${entityClassName?cap_first}.insert${entityName}", ${entityClassName});
    }

    void update${entityName}(${entityClassName} entity){
        daoRouter.insert("${entityClassName?cap_first}.update${entityName}", ${entityClassName});
    }

    void delete${entityName}(${entityClassName} entity){
        daoRouter.insert("${entityClassName?cap_first}.delete${entityName}", ${entityClassName});
    }

    List<${entityClassName}> load${entityName}(Map<String, Object> params){
        return daoRouter.query("${entityClassName?cap_first}.load${entityName}",params);
    }

    @Autowired
    private IDaoRouter daoRouter;

}