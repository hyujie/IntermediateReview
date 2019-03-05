package com.hyj.intermediate.proxy.staticproxy;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 14:39 2019/3/5
 */
public class MyMath implements Math {
    @Override
    public int add(int num1, int num2) {

        return num1+num2;
    }

    @Override
    public int sub(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public int mul(int num1, int num2) {
        return num1*num2;
    }

    @Override
    public int div(int num1, int num2) {
        return num1/num2;
    }
}
