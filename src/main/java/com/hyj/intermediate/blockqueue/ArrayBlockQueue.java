package com.hyj.intermediate.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 9:56 2019/3/6
 */
public class ArrayBlockQueue<T> {
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;
    private Object[] objects;

    private int putIndex = 0;
    private int takeIndex = 0;

    private int capacity = 10;
    private int count = 0;

    public ArrayBlockQueue(int capacity){
        this.capacity = capacity;
        this.lock = new ReentrantLock(true);
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
        this.objects = new Object[capacity];
    }

    public void put(T value) throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while (count == objects.length){
                notFull.await();
            }
            //添加
            final Object[] temp = this.objects;
            temp[putIndex] = value;
            if((++putIndex) == temp.length){
                putIndex = 0;
            }
            count++;
            System.out.println(count);
            notEmpty.signal();

        }finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException{
        final ReentrantLock lock = this.lock;
        lock.lock();
        while (count == 0){
            notEmpty.await();
        }
        final Object[] temp = this.objects;
        T result = (T)temp[takeIndex];
        temp[takeIndex] = null;
        if(++takeIndex == temp.length){
            takeIndex = 0;
        }
        count--;
        notFull.signal();
        return result;
    }


    public static void main(String[] args) throws InterruptedException{
        ArrayBlockQueue<Integer> arrayBlockQueue = new ArrayBlockQueue<>(3);
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    arrayBlockQueue.put(1);
                    arrayBlockQueue.put(2);
                    arrayBlockQueue.put(3);
                    arrayBlockQueue.put(4);

                }catch (InterruptedException e){
                                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(arrayBlockQueue.take()+"a");
                    System.out.println(arrayBlockQueue.take()+"a");
                    System.out.println(arrayBlockQueue.take()+"a");
                    System.out.println(arrayBlockQueue.take()+"a");
                }catch (InterruptedException e){

                }
            }
        });
        threadA.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        threadB.start();

    }
}
