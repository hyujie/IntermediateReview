package com.hyj.intermediate.designPattern.decorator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description 具体构建类
 * @Date Create by in 17:58 2019/3/15
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        beverageName = "混合咖啡";
    }
    @Override
    public Integer cost() {
        return null;
    }
}
