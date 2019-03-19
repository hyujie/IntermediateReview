package com.hyj.intermediate.designPattern.singleton;


/**
 * 单例模式 懒汉模式  只适合单线程
 */
public class SingletonOne {

    private static SingletonOne singletonOne = null;
    private SingletonOne(){

    }

    public static SingletonOne getInstance(){
        if(singletonOne == null){
            singletonOne = new SingletonOne();
        }
        return singletonOne;
    }
}
