package com.hyj.intermediate.designPattern.singleton;

/**
 * 基于枚举的单例模式
 */
public enum SingletonEnum {
    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
