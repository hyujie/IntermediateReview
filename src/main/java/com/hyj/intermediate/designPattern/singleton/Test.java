package com.hyj.intermediate.designPattern.singleton;

public class Test {
    private  int x =1;
    private static  class test1{
        private  static final Test t = new Test();

        static{
            System.out.println("=======");
        }
    }

    public Test getTest(){
        return  test1.t;
    }

    public static void main(String[] args) {
        new Test();
    }
}
