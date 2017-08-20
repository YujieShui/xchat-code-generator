package ${packageStr};

public interface ${className}{

    void insert${entityName}(${entityClassName} entity);

    void update${entityName}(${entityClassName} entity);

    void delete${entityName}(${entityClassName} entity);

    List<${entityClassName}> load(Searchable<Map<String, Object> params);

}