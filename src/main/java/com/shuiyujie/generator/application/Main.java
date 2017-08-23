package com.shuiyujie.generator.application;

import com.shuiyujie.generator.task.*;

/**
 * created by shui 2017/8/23
 */
public class Main {

    public static void main(String[] args) throws Exception {
        new VOTask().doInternale();
        new DaoTask().doInternale();
        new IDaoTask().doInternale();
        new MapperTask().doInternale();
        new PbTask().doInternale();
        new PbTransferTask().doInternale();
    }
}
