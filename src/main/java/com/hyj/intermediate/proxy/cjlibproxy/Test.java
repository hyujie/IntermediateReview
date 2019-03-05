package com.hyj.intermediate.proxy.cjlibproxy;

import com.hyj.intermediate.proxy.staticproxy.MyMath;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 15:42 2019/3/5
 */
public class Test {

    @org.junit.Test
    public void test(){
        CglibProxy cglibProxy = new CglibProxy();
        MyMath myMath = (MyMath)cglibProxy.getTargetObject(new MyMath());
        System.out.println(myMath.add(1,1));
    }
}
