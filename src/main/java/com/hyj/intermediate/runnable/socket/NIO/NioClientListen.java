package com.hyj.intermediate.runnable.socket.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 10:11 2019/3/20
 */
public class NioClientListen implements Runnable {

    private Selector selector;
    private int port = 8088;
    private SocketChannel socketChannel;
    public synchronized void initClient(){
        try{
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost",port));
        }catch (IOException e){
         e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            this.selector = Selector.open();
            this.initClient();
        }catch (IOException e){
            e.printStackTrace();
        }
        listen();
    }

    public void listen(){
        while(true){
            try{
                this.selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while(keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();
                    if(key.isValid()){
                        if(key.isConnectable()){
                            SocketChannel socketChannel = (SocketChannel)key.channel();
                            while (!socketChannel.finishConnect()){
                                TimeUnit.SECONDS.sleep(1);
                            }
                            key.interestOps(SelectionKey.OP_READ);
                            System.out.println("连接成功");
                        }
                        if(key.isReadable()){
                            this.read(key);
                        }
                    }
                }
            }catch (IOException | InterruptedException e){

            }

        }
    }

    public void read(SelectionKey key){
        SocketChannel sc =(SocketChannel) key.channel();
        ByteBuffer readbyte = ByteBuffer.allocate(1024);
        try{
            int count = sc.read(readbyte);
            if(count == -1){
                key.channel().close();
                key.cancel();
            }
            System.out.println("!!!!");
            readbyte.flip();
            byte[] data = new byte[readbyte.remaining()];
            readbyte.get(data);
            System.out.println("收到服务器的消息："+(new String(data)));
            key.interestOps();
        }catch (IOException e){
            try{
                key.channel().close();
                key.cancel();
            }catch (IOException ex){
                ex.printStackTrace();
            }

        }
    }
    public void write(SocketChannel socketChannel){
        String data = "hello";
        byte[] bytaData = data.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(bytaData);
        byteBuffer.flip();
        try{
            while(byteBuffer.hasRemaining()){
                socketChannel.write(byteBuffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args)throws Exception  {
        NioClientListen nioClientListen = new NioClientListen();
       // nioClientListen.initClient();
        new Thread(nioClientListen).start();
        TimeUnit.SECONDS.sleep(6);
        nioClientListen.write(nioClientListen.socketChannel);
    }
}
