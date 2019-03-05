package com.hyj.intermediate.proxy.cjlibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.time.Clock;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 15:38 2019/3/5
 */
public class CglibProxy implements MethodInterceptor {

    public Object targetObject;
    public Object getTargetObject(Object targetObject){
        this.targetObject = targetObject;
        //增强器，动态代码生成器
        Enhancer enhancer = new Enhancer();
        //设置回调方法
        enhancer.setCallback(this);
        //设置生成类的父类类型
        enhancer.setSuperclass(targetObject.getClass());
        return enhancer.create();


    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = Clock.systemUTC().millis();
        Object result =methodProxy.invoke(targetObject,objects);
        long end = Clock.systemUTC().millis();
        System.out.println("方法用时："+(end - start));
        return result;
    }
}
