package ${idao.packageName};

import com.value.mybatis.IDaoRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ${idao.className}Service implements I${idao.className} {

    @Override
    public void insert${idao.className}(${idao.className}VO vo){
        daoRouter.insert("${idao.className}.insert${idao.className}", vo);
    }

    @Override
    public void update${idao.className}(${idao.className}VO vo){
        daoRouter.update("${idao.className}.${idao.className}", vo);
    }

    @Override
    public void delete${idao.className}(${idao.className}VO vo){
        daoRouter.update("${idao.className}.delete${idao.className}", vo);
    }

    @Override
    public List<${idao.className}VO> load${idao.className}(Map<String, Object> params){
        return daoRouter.query("${idao.className}.load${idao.className}",params);
    }

    @Autowired
    private IDaoRouter daoRouter;

}