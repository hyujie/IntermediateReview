package com.hyj.intermediate.designPattern.decorator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description  装饰者模式
 * 动态地为一个对象添加一些额外的职责，若要扩展一个对象的功能，
 * 装饰者提供了比继承更有弹性的替代方案
 * @Date Create by in 17:41 2019/3/15
 */
public class Decorator {
    public static void main(String[] args) {
        Beverage DarkRoast = new DarkRoast();
        Milk milk = new Milk(DarkRoast);
        Mocha mocha = new Mocha(DarkRoast);
        System.out.println(milk.getDescription());
        System.out.println(mocha.getDescription());
        Beverage HouseBlend = new HouseBlend();
        Milk milk1 = new Milk(HouseBlend);
        Mocha mocha1 = new Mocha(HouseBlend);
        System.out.println(milk1.getDescription());
        System.out.println(mocha1.getDescription());
    }

}
