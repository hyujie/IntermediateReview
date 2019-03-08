package com.hyj.intermediate.runnable.callback;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 15:36 2019/3/7
 */
public class AtomicCallable {
    AtomicStampedReference atomicReference = new AtomicStampedReference<Integer>(100,0);
    class AtomicRef1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            atomicReference.compareAndSet(100,101,atomicReference.getStamp(),atomicReference.getStamp()+1);
            atomicReference.compareAndSet(101,100,atomicReference.getStamp(),atomicReference.getStamp()+1);
            return (Integer) atomicReference.getReference();
        }
    }

    class AtomicRef2 implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            int atomicversion = atomicReference.getStamp();
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100,101,atomicversion,atomicversion+1);
            return (Integer) atomicReference.getReference();
        }
    }

    @Test
    public void test()throws Exception{
        FutureTask futureTask = new FutureTask(new AtomicRef1());
        FutureTask futureTask2 = new FutureTask(new AtomicRef2());
        new Thread(futureTask).start();
        new Thread(futureTask2).start();
        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());
    }

    @Test
    public void test2()throws Exception{
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad");
        ProcessBuilder processBuilder = new ProcessBuilder("calc");
        processBuilder.start();


    }
}
