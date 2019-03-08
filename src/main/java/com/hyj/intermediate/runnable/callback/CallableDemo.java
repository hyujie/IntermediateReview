package com.hyj.intermediate.runnable.callback;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 10:24 2019/3/7
 */
public class CallableDemo implements Callable<Integer> {

    private AtomicInteger atomicInteger;

    public CallableDemo(){
        atomicInteger = new AtomicInteger(0);
    }
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return atomicInteger.addAndGet(1);
    }

    @org.junit.Test
    public void test() {
        CallableDemo callableDemo = new CallableDemo();
        //执行Callable 方式，需要FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo);
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(callableDemo);
        new Thread(futureTask).start();
        new Thread(futureTask2).start();

        try{
            //FutureTask也可用于闭锁，在线程运算的过程中，运算的结果是没有打印的，result.get() 的操作是没有运行的
            System.out.println("======");
            Integer sum = futureTask.get();
            Integer sum2 = futureTask2.get();
            System.out.println(sum);
            System.out.println(sum2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future =  service.submit(new CallableDemo());

        try{
            System.out.println(future.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(service);
        CallableDemo callableDemo = new CallableDemo();
        for(int i = 0;i<10;i++){
            completionService.submit(callableDemo);
        }

        try {
            for(int i = 0;i<10;i++){
                int result = completionService.take().get();
                System.out.println(result);
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }


}
