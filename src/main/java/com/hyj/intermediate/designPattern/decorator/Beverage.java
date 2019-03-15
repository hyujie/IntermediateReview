package com.hyj.intermediate.designPattern.decorator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description  抽象构件类
 * @Date Create by in 17:51 2019/3/15
 */
public abstract class Beverage {

    public String beverageName = "未知饮料";

    public String getBeverageName(){
        return beverageName;
    }
    public abstract Integer cost();

}
