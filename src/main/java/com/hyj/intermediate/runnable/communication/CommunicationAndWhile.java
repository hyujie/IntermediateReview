package com.hyj.intermediate.runnable.communication;

import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 10:18 2019/3/8
 */
public class CommunicationAndWhile {

    static List<Integer> list = new ArrayList<Integer>();

    public List getList() {
        return list;
    }

    public void add(int i){
        list.add(i);
    }

    public int getLength(){
        return list.size();
    }

    class RunA implements Runnable{
        @Override
        public void run(){
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            for(int i = 0;i<10;i++){
                add(i);
            }
            System.out.println(getLength());
        }
    }
    class RunB implements Runnable{
        @Override
        public void run(){
            System.out.println("以进入代码块");
            boolean bool = true;
            while (bool){
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(getLength());
                if(getLength() == 10){
                    System.out.println("获取跳出循环通知");
                    bool = false;
                }
            }
        }
    }

    @Test
    public void test(){

    }


    public static void main(String[] args) throws Exception{
        RunA runA = new CommunicationAndWhile().new RunA();
        RunB runB = new CommunicationAndWhile().new RunB();
        new Thread(runA).start();
        new Thread(runB).start();

    }
}
