package ${protobuf.name};

option java_package = "${protobuf.name}";

option java_outer_classname = "${protobuf.proName?cap_first}Proto";


message ${protobuf.proName?cap_first}ListPb {
    repeated ${protobuf.proName?cap_first}Pb ${protobuf.proName} = 1;
}


message ${protobuf.proName?cap_first}Pb {
    <#list vo.pbInfoList as pb>
        optional ${pb.type} ${pb.name} = ${item_index + 1};
    </#list>
}

}




