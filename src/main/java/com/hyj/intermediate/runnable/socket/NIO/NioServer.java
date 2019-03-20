package com.hyj.intermediate.runnable.socket.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 10:02 2019/3/19
 */
public class NioServer implements Runnable{
    /**
     * 多路复用器，管理所有通道
     */
    private Selector selector;

    /**
     * 读写缓存区
     */
    private ByteBuffer writeBuffer;
    private ByteBuffer readBuffer;

    public NioServer(int port){
        //申请缓存空间
        writeBuffer = ByteBuffer.allocate(1024);
        readBuffer = ByteBuffer.allocate(1024);
        try{
            //打开多路复用器
            this.selector = Selector.open();
            //打开服务器通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置服务器通道为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            //每个管道都会对选择器进行注册不同的事件状态，以便选择器查找。
            //
            //SelectionKey.OP_CONNECT          连接状态
            //
            //SelectionKey.OP_ACCEPT            阻塞状态
            //
            //SelectionKey.OP_READ              可读状态
            //
            //SelectionKey.OP_WRITE             可写状态
            //---------------------
            //把管道服务器注入到多路复用器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        int count = 0;
        while(true){
            try {
                //必须让多路复用器开始监听
                System.out.println("开启多路复用器监听！！");
                this.selector.select();
                //获取多路复用器已经选择的结果集
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                System.out.println("轮询次数："+count++);
                //遍历
                while(keys.hasNext()){
                    //获取一个
                    SelectionKey key = keys.next();
                    //直接剔除
                    keys.remove();
                    //判断key是否有效
                    if(key.isValid()){
                        //是否是阻塞状态
                        if(key.isAcceptable()){
                            System.out.println("accept");
                            this.accept(key);
                            //
                        }
                        //是否是可写入状态
                        if(key.isWritable()){

                            System.out.println(key.attachment()
                                    + " - 写数据事件");
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            ByteBuffer sendBuf = ByteBuffer.allocate(1024);
                            String sendText = "hello\n";
                            sendBuf.put(sendText.getBytes());
                            sendBuf.flip();        //写完数据后调用此方法
                            TimeUnit.SECONDS.sleep(5);
                            while(sendBuf.hasRemaining()){
                                clientChannel.write(sendBuf);
                            }
                            clientChannel.register(this.selector,SelectionKey.OP_READ);
                            //
                        }
                        //是否是可读状态
                        if(key.isReadable()){
                            System.out.println("read");
                            this.read(key);
                        }
                    }
                }
            }catch (IOException|InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key){
        try{
            //清空缓存区遗留数据
            this.readBuffer.clear();
            //获取之前注册的socket管道
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //读取数据
            int count = socketChannel.read(readBuffer);
            if(count == -1){
                key.channel().close();
                key.cancel();
                return;
            }
            //如果有数据要把readbuffer复位（主要是position和limit进行复位）
            this.readBuffer.flip();
            //根据缓存区的数据长度设置相应的数组
            byte[] bytes = new byte[this.readBuffer.remaining()];
            //接收缓存区的数据
            readBuffer.get(bytes);
            //答应结果
            String data = new String(bytes).trim();
            System.out.println(data);
            //可以写数据返回给客服端
            socketChannel.register(this.selector,SelectionKey.OP_WRITE);
        }catch (IOException e){
            try{
                key.channel().close();
                key.cancel();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }


    private void accept(SelectionKey key){
        //获取服务通道
        System.out.println("第一次！！");
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
        //执行阻塞方法
        try{
            SocketChannel socketChannel = ssc.accept();
            //设置非阻塞模式
            socketChannel.configureBlocking(false);
            //4 注册到多路复用器上，并设置读取标识
            socketChannel.register(this.selector,SelectionKey.OP_READ);

        }catch (IOException e){
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        new Thread(new NioServer(8088)).start();
    }
}
