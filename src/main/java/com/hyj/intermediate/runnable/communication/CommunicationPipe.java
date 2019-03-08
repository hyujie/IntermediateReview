package com.hyj.intermediate.runnable.communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description 管道流通信
 * @Date Create by in 15:25 2019/3/8
 */
public class CommunicationPipe {
    PipedInputStream pipedInputStream;
    PipedOutputStream pipedOutputStream;

    public CommunicationPipe(){
        this.pipedInputStream = new PipedInputStream();
        this.pipedOutputStream = new PipedOutputStream();
    }
    public PipedInputStream getPipeIn(){
        return pipedInputStream;
    }

    public PipedOutputStream getPipeOut(){
        return pipedOutputStream;
    }

    public PipedInt getThreadIn(){
        return new PipedInt();
    }

    public PipedOut getThreadOut(){
        return new PipedOut();
    }
    class PipedInt implements Runnable{
        @Override
        public void run(){
            byte[] bytes = new byte[20];
            try{
                int num =pipedInputStream.read(bytes);
                while (num != -1){
                    String data = new String(bytes,0,bytes.length);
                    System.out.println("-"+data);
                    num = pipedInputStream.read(bytes);
                }
                System.out.println();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(pipedInputStream != null){
                    try{
                        System.out.println("等out关闭");
                        pipedInputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    class PipedOut implements Runnable{
        @Override
        public void run(){
            try{
            for(int i = 0;i<10;i++){
                String data = i+"";
                TimeUnit.MILLISECONDS.sleep(900);
                    pipedOutputStream.write(data.getBytes());

                    System.out.print(data);
                }
                System.out.println();

            }catch (IOException|InterruptedException e){
                e.printStackTrace();
            }finally {
                try{
                    if(pipedOutputStream != null){
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("我关闭了！！");
                        pipedOutputStream.close();
                    }
                }catch (IOException|InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        CommunicationPipe communicationPipe = new CommunicationPipe();
        communicationPipe.getPipeIn().connect(communicationPipe.getPipeOut());
        new Thread(communicationPipe.getThreadOut()).start();
        //TimeUnit.SECONDS.sleep(2);
        new Thread(communicationPipe.getThreadIn()).start();

    }
}
