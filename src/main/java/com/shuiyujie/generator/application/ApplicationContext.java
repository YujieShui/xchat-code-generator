package com.shuiyujie.generator.application;

import java.util.HashMap;
import java.util.Map;

/**
 * created by shui 2017/8/19
 */
public abstract class ApplicationContext {

    Map<String,Object> contexts = new HashMap<>();

    abstract void setAttribute();

    abstract void getAttritube();
}
