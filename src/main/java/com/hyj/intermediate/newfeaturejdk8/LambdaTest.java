package com.hyj.intermediate.newfeaturejdk8;

import com.sun.org.glassfish.gmbal.ParameterNames;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 10:56 2019/2/20
 */
public class LambdaTest {
    public static void main(String[] args) throws Exception{
        Arrays.asList(1,2,3,4).forEach(e -> System.out.println(e));
        Arrays.asList(1,2,3,4).forEach(e -> {
            System.out.println(e);
            System.out.println(e+1);

        });
        Method method = LambdaTest.class.getMethod( "test", String.class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }

    }

    public void test(String ss){

    }
}
