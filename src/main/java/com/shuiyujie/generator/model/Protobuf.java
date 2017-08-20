package com.shuiyujie.generator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * created by shui 2017/8/20
 */
public class Protobuf extends TableInfo{

    // ColumnInfo to PbInfor
    private List<PbInfo> pbInfoList;

    public List<PbInfo> getPbInfoList() {
        return pbInfoList;
    }

    public void setPbInfoList(List<PbInfo> pbInfoList) {
        this.pbInfoList = pbInfoList;
    }
}
