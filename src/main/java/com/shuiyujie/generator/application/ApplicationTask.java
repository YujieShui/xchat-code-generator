package com.shuiyujie.generator.application;

/**
 * created by shui 2017/8/19
 * //
 */
public abstract class ApplicationTask extends ApplicationContext {

    @Override
    public void setAttribute(String key, Object value) {
        contexts.put(key, value);
    }

    @Override
    public Object getAttritube(String key) {
        return contexts.get("key");
    }

}
