package ${idao.packageName};

import com.value.mybatis.IDaoRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${idao.className} implements I${idao.className} {

    void insert${idao.className}(${idao.className}VO vo){
        daoRouter.insert("${idao.className}.insert${idao.className}", vo);
    }

    void update${idao.className}(${idao.className}VO vo){
        daoRouter.update("${idao.className}.${idao.className}", vo);
    }

    void delete${idao.className}(${idao.className}VO vo){
        daoRouter.update("${idao.className}.delete${idao.className}", vo);
    }

    List<${idao.className}VO> load${idao.className}(Map<String, Object> params){
        return daoRouter.query("${idao.className}.load${idao.className}",params);
    }

    @Autowired
    private IDaoRouter daoRouter;

}