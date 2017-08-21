package ${protobuf.packageName};

option java_package = "${protobuf.packageName}";

option java_outer_classname = "${protobuf.name?cap_first}Proto";

    message ${protobuf.name?cap_first}ListPb {
        repeated ${protobuf.name?cap_first}Pb ${protobuf.name} = 1;
    }

    message ${protobuf.name?cap_first}Pb {
        <#list protobuf.classColumnList as pb>
            optional ${pb.type} ${pb.name} = ${pb_index + 1};
        </#list>
    }

}