package com.hyj.intermediate.runnable.communication;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import sun.security.provider.MD5;

import javax.xml.stream.events.Characters;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description 生产者消费者模式
 * @Date Create by in 11:04 2019/3/8
 */
public class PandC {
    Object lock = new Object();
    int value = 0;


    public Producer getProducer(){
        return new Producer();
    }

    public Consumer getConsumer(){
        return new Consumer();
    }
    class Producer implements Runnable{
        @Override
        public void run(){
            while (true){
                synchronized (lock){
                    if(value == 1){
                        try{
                            lock.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("生产前产品："+value);
                    value++;
                    System.out.println("生成后产品数量："+value);
                    lock.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run(){
                while (true){
                    synchronized (lock){
                    if(value == 0){
                        try{
                            lock.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("消费前产品："+value);
                    value--;
                    System.out.println("消费后前产品："+value);
                    lock.notifyAll();
                }
            }

        }
    }


    public static void main(String[] args) throws Exception{
        PandC pandC = new PandC();
        new Thread(pandC.getProducer()).start();
        //TimeUnit.SECONDS.sleep(2);
        new Thread(pandC.getConsumer()).start();
    }
}
