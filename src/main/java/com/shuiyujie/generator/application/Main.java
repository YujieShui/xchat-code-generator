package com.shuiyujie.generator.application;

import com.shuiyujie.generator.task.*;

import java.lang.reflect.Field;

/**
 * created by shui 2017/8/23
 */
public class Main {

//    public static void main(String[] args) throws Exception {
//        new VOTask().doInternale();
//        new DaoTask().doInternale();
//        new IDaoTask().doInternale();
//        new MapperTask().doInternale();
//        new PbTask().doInternale();
//        new PbTransferTask().doInternale();
//    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Integer a = 1, b = 2;
        System.out.println("a="+a+" b="+b); //a=1 b=2
        swap(a,b);
        System.out.println("a="+a+" b="+b);//a=2 b=1
    }

    private static void swap(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
        Field field = Integer.class.getDeclaredField("value");

        field.setAccessible(true);

        Integer temp = new Integer(a.intValue());

        field.set(a, b.intValue());

        field.set(b, temp);
    }
}
