package com.hyj.intermediate.designPattern.decorator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 18:04 2019/3/15
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getBeverageName()+"摩卡";
    }

    @Override
    public Integer cost() {
        return beverage.cost()+5;
    }
}
