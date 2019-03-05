package com.hyj.intermediate.proxy.jdkproxy;

import com.hyj.intermediate.proxy.staticproxy.Math;
import com.hyj.intermediate.proxy.staticproxy.MyMath;
import sun.plugin2.message.Message;
import sun.plugin2.message.Serializer;

import java.io.IOException;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 14:54 2019/3/5
 */
public class Test {

    @org.junit.Test
    public void test(){
        MyMath myMath = new MyMath();
        MyJdkProxy myJdkProxy = new MyJdkProxy();
        Math math = (Math) myJdkProxy.getTargetObject(myMath);
        System.out.println(math.add(1,1));

    }
}
