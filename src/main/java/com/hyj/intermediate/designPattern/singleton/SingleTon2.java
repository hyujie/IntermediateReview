package com.hyj.intermediate.designPattern.singleton;

/**
 * 懒汉模式，
 * 缺点：
 * //都需要额外的工作(Serializable、transient、readResolve())来实现序列化，否则每次反序列化一个序列化的对象实例时都会创建一个新的实例。
 * 可能会有人使用反射强行调用我们的私有构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）。
 */
public class SingleTon2 {

    private static SingleTon2 instance;

    private volatile static SingleTon2 instance2;


    //同步方法模式 效率低
    public  static synchronized SingleTon2 getInstance(){
        if(instance == null){
            instance = new SingleTon2();
        }
        return instance;
    }
    //双重检索模式（ instance = new SingleTon2();指令重排导致多线程下，有可能拿到没有初始化属性的对象）
    //所以要用volidate
    public static SingleTon2 getInstance2(){
        if(instance2 == null){
            synchronized (SingleTon2.class){
                if(instance2 == null){
                    instance2 = new SingleTon2();
                }
            }
        }
        return instance2;
    }

}
