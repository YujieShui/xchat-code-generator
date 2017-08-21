package ${packageStr};

import com.value.mybatis.IDaoRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class I${idao.className} implements I${idao.className} {

    void insert${idao.className}(${idao.className}VO vo){
        daoRouter.insert("${entityClassName?cap_first}.insert${entityName}", ${entityClassName});
    }

    void update${idao.className}(${idao.className}VO vo){
        daoRouter.update("${entityClassName?cap_first}.update${entityName}", ${entityClassName});
    }

    void delete${idao.className}(${idao.className}VO vo){
        daoRouter.update("${entityClassName?cap_first}.update${entityName}", ${entityClassName});
    }

    List<${idao.className}VO> load${idao.className}(Map<String, Object> params){
        return daoRouter.query("${entityClassName?cap_first}.load${entityName}",params);
    }

    @Autowired
    private IDaoRouter daoRouter;

}