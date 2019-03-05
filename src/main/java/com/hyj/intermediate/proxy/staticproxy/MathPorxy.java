package com.hyj.intermediate.proxy.staticproxy;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 14:40 2019/3/5
 */
public class MathPorxy {
    MyMath myMath = new MyMath();

    public int add(int num1,int num2){

        return myMath.add(num1,num2);
    }

    public int sub(int num1,int num2){
        return myMath.sub(num1,num2);
    }

    public int mul(int num1,int num2){
        return myMath.mul(num1,num2);
    }

    public int div(int num1,int num2){
        return myMath.div(num1,num2);
    }

//5.1、解决了“开闭原则（OCP）”的问题，因为并没有修改Math类，而扩展出了MathProxy类。
//
//5.2、解决了“依赖倒转（DIP）”的问题，通过引入接口。
//
//5.3、解决了“单一职责（SRP）”的问题，Math类不再需要去计算耗时与延时操作，但从某些方面讲MathProxy还是存在该问题。
//
//    未解决：
//5.4、如果项目中有多个类，则需要编写多个代理类，工作量大，不好修改，不好维护，不能应对变化。
    public static void main(String[] args) {
        MathPorxy mathPorxy = new MathPorxy();
        System.out.println(mathPorxy.add(1,2));
    }
}
