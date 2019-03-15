package com.hyj.intermediate.runnable.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 17:58 2019/3/8
 */
public class Client {

    public void RunClient(){
        try{
            Socket socket = new Socket("localhost",10086);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("hello！！");
            bufferedWriter.flush();
            socket.shutdownOutput();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = null;
            String info = "";
            while ((temp = bufferedReader.readLine()) != null){
                info+=temp;
                System.out.println("已接收到服务器的info:"+info);
                System.out.println("当前服务器端的IP："+socket.getInetAddress().getHostAddress());
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
            socket.close();
        }catch (IOException e){

        }

    }

    public static void main(String[] args) {
        new Client().RunClient();
    }
}
