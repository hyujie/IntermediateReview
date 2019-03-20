package com.hyj.intermediate.runnable.socket.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 11:02 2019/3/19
 */
public class NioClient implements Runnable {

    @Override
    public void run(){
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8088);
        SocketChannel sc = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try{
            sc = SocketChannel.open();
           // sc.configureBlocking(false);
            sc.connect(inetSocketAddress);
            while(sc.finishConnect() ){
                byte[] bytes = new byte[1024];
                System.out.println("请输入需要传递的数据：");
                System.in.read(bytes);
                byteBuffer.put(bytes);
               // byteBuffer.position(200);
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    sc.write(byteBuffer);
                }
                byteBuffer.clear();
                readBuffer.clear();
                System.out.println(readBuffer.hasRemaining());
//                    while (readBuffer.hasRemaining()){
//                       int count =  sc.read(readBuffer);
//                       System.out.println(count);
//                    }

                System.out.println("====");
                readBuffer.flip();
                byte[] bytes1 = new byte[readBuffer.remaining()];
                readBuffer.get(bytes1);
                String data = new String(bytes1);
                readBuffer.clear();
                System.out.println(data);


            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(sc != null){
                try{
                    sc.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new NioClient()).start();
    }
}
