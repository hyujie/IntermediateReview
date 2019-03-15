package com.hyj.intermediate.runnable.socket;

import java.io.IOException;
import java.net.*;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 16:33 2019/3/15
 */
public class UDPClient {

    public void runClient(){
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            int port = 8080;
            byte[] bytes = new byte[1024];
            bytes = "appId：1000".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,inetAddress,port);
            socket.send(datagramPacket);

            bytes = new byte[1024];
            DatagramPacket datagramPacket1 = new DatagramPacket(bytes,bytes.length);
            socket.receive(datagramPacket1);
            System.out.println(new String(bytes,0,bytes.length));
            socket.close();
        }catch (SocketException | UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new UDPClient().runClient();
    }
}
