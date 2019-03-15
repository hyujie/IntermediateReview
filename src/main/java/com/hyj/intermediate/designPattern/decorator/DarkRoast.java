package com.hyj.intermediate.designPattern.decorator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description 具体构建类
 * @Date Create by in 17:54 2019/3/15
 */
public class DarkRoast extends  Beverage {

    public DarkRoast(){
        beverageName = "深焙咖啡";
    }
    @Override
    public Integer cost() {
        return 10;
    }
}
