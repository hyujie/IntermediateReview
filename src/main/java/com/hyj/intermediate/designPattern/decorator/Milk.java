package com.hyj.intermediate.designPattern.decorator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description  具体装饰类
 * @Date Create by in 18:00 2019/3/15
 */
public class Milk extends CondimentDecorator {
    Beverage beverage;
    public Milk(Beverage beverage){
        this.beverage =beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getBeverageName()+",牛奶";
    }

    @Override
    public Integer cost() {
        return beverage.cost()+3;
    }
}
