package ${idao.packageName};

public interface I${idao.className}Service{

    void insert${idao.className}(${idao.className}VO vo);

    void update${idao.className}(${idao.className}VO vo);

    void delete${idao.className}(${idao.className}VO vo);

    List<${idao.className}VO> load${idao.className}(Map<String, Object> params);

}