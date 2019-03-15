package com.hyj.intermediate.runnable.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 17:50 2019/3/8
 */
public class Server {
    Socket socket;
    boolean bool = true;
    public void RunServer(){
        try{
            ServerSocket serverSocket = new ServerSocket(10086);

            while (bool){
                socket = serverSocket.accept();
                new Thread(new SocketRun()).start();
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    class SocketRun implements Runnable{
        @Override
        public void run(){
            try{
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp = null;
                String info = "";
                while ((temp = bufferedReader.readLine()) != null){
                    info+=temp;
                    System.out.println("已接收到的info:"+info);
                    System.out.println("当前客服端的IP："+socket.getInetAddress().getHostAddress());
                    System.out.println("当前客服端port:"+socket.getPort());
                }


                OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
                PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
                if("可以停止接听了!!".equals(info)){
                    printWriter.print("你好，服务端将关闭接听！！");
                }else{
                    printWriter.print("你好，服务端已接收到您的信息");
                }
                printWriter.flush();
                socket.shutdownOutput();//关闭输出流
                printWriter.close();
                outputStream.close();
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new Server().RunServer();
    }
}
