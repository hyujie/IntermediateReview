package com.hyj.intermediate.runnable.communication;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 10:48 2019/3/8
 */
public class CommunicationWait {
    static Object lock = new Object();
    static int num = 0;

    public RunA getRunA(){
        return new RunA();
    }
    public RunB getRunB(){
        return new RunB();
    }

    class RunA implements Runnable{
        @Override
        public void run(){
            synchronized(lock){
                try{
                    System.out.println(num);
                    while (num == 0){
                        System.out.println("num == 0  进入阻塞状态释放锁");
                        lock.wait();
                    }
                    System.out.println(num);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

    class RunB implements Runnable{
        @Override
        public void run(){
            synchronized (lock){
                num++;
                lock.notify();
            }

        }
    }

    public static void main(String[] args) throws Exception{
        CommunicationWait communicationWait = new CommunicationWait();
        new Thread(communicationWait.getRunA()).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(communicationWait.getRunB()).start();
    }
}
