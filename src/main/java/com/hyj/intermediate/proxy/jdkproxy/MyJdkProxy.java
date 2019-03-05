package com.hyj.intermediate.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Clock;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 14:48 2019/3/5
 */
public class MyJdkProxy implements InvocationHandler {

    Object targetObject;

    public Object getTargetObject(Object targetObject){

        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
                ,targetObject.getClass().getInterfaces(),this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("add".equals(method.getName())){
            System.out.println("当前执行是加法！！！");
        }
        long start = Clock.systemUTC().millis();
        Object result =  method.invoke(targetObject,args);
        long end = Clock.systemUTC().millis();
        System.out.println("执行总用时："+(end - start));
        return result;
    }
}
