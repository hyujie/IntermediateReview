package com.hyj.intermediate.designPattern.singleton;

/**
 * 饿汉模式
 */
public class SingleTonThehungry {

    private static SingleTonThehungry instance = new SingleTonThehungry();

    private SingleTonThehungry(){

    }
    public static SingleTonThehungry getInstance(){
        return instance;
    }
    //使用私有静态内部类方式的饿汉模式
    // Effective Java 第一版推荐写法
    private static class StaticSingleTon{
        private static SingleTonThehungry instance = new SingleTonThehungry();
        static {
            System.out.println("哈哈");
        }
    }
    public static SingleTonThehungry getInstance2(){
        return StaticSingleTon.instance;
    }

    public static void main(String[] args) {
    }
}
