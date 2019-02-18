package com.hyj.intermediate.syn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 16:33 2019/2/18
 */
public class Mutex implements Lock,java.io.Serializable {

    private static final long serialVersionUID = 5477694732449215659L;

    /**
     * 自定义同步器
     */
    private static class Syn extends AbstractQueuedSynchronizer{

        /**
         * 判断状态是否锁定
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg ==1;
            if(!(getState() == 0)){
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
            return false;
        }
    }
    // 真正同步类的实现都依赖继承于AQS的自定义同步器！
    private final Syn syn = new Syn();
    public void lock() {
        syn.acquire(1);
    }

    public void lockInterruptibly() throws InterruptedException {
        syn.isHeldExclusively();
    }

    public boolean tryLock() {
        return syn.tryAcquire(1);
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        syn.release(1);
    }

    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }



}
