package com.hyj.intermediate.runnable.socket;

import java.io.IOException;
import java.net.*;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 16:26 2019/3/15
 */
public class UDPServer {

    public void runServer(){
        try{
            DatagramSocket socket = new DatagramSocket(8080);
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
            socket.receive(datagramPacket);
            String data = new String(bytes,0,bytes.length);
            System.out.println("收到数据："+data);

            InetAddress inetAddress = datagramPacket.getAddress();
            int port  = datagramPacket.getPort();
            byte[] bytes1 = new byte[1024];
            bytes1 = "已接受的数据！！".getBytes();
            DatagramPacket datagramPacket1 = new DatagramPacket(bytes1,bytes1.length,inetAddress,port);
            socket.send(datagramPacket1);
            socket.close();
        }catch (SocketException  e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new UDPServer().runServer();
    }
}
