package com.hyj.intermediate.syn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 17:47 2019/2/21
 */
public class OptimisticLocking{
    AtomicInteger atomicInteger = new AtomicInteger(0);

    int count = 0;
    public void add(){
        atomicInteger.addAndGet(1);
    }
    public void dec(){
        atomicInteger.decrementAndGet();
    }
    public int get(){
       return atomicInteger.get();
    }



    class AddCount implements Runnable{
        OptimisticLocking optimisticLocking;
        public AddCount(OptimisticLocking optimisticLocking){
            this.optimisticLocking = optimisticLocking;
        }
        @Override
        public void run() {
            for(int i = 0;i<10000;i++){
                optimisticLocking.add();
            }
        }
    }
    class DecCount implements Runnable{
        OptimisticLocking optimisticLocking;
        public DecCount(OptimisticLocking optimisticLocking){
            this.optimisticLocking = optimisticLocking;
        }
        @Override
        public void run() {
            for(int i = 0;i<10001;i++){
                optimisticLocking.dec();
            }
        }
    }

    public void test()throws Exception{
        OptimisticLocking optimisticLocking = new OptimisticLocking();
        AddCount addCount = new AddCount(optimisticLocking);
        DecCount decCount = new DecCount(optimisticLocking);
        Thread thread  = new Thread(addCount);
        Thread thread2  = new Thread(decCount);
        thread.start();
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(5);
        System.out.println(optimisticLocking.get());
    }
    public static void main(String[] args) throws Exception{
        OptimisticLocking optimisticLocking = new OptimisticLocking();
        optimisticLocking.test();
    }
}
