package ${idao.packageName};

import com.value.mybatis.IDaoRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ${idao.className}Service implements I${idao.className}Service {

    @Override
    public void insert${idao.className}(${idao.className}VO ${idao.className?uncap_first}VO){
        daoRouter.insert("${idao.className}.insert${idao.className}", ${idao.className?uncap_first}VO);
    }

    @Override
    public void update${idao.className}(${idao.className}VO ${idao.className?uncap_first}VO){
        daoRouter.update("${idao.className}.update${idao.className}", ${idao.className?uncap_first}VO);
    }

    @Override
    public void delete${idao.className}(${idao.className}VO ${idao.className?uncap_first}VO){
        daoRouter.update("${idao.className}.delete${idao.className}", ${idao.className?uncap_first}VO);
    }

    @Override
    public List<${idao.className}VO> load${idao.className}(Map<String, Object> params){
        return daoRouter.query("${idao.className}.load${idao.className}",params);
    }

    @Autowired
    private IDaoRouter daoRouter;

}