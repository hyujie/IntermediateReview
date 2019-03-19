package com.hyj.intermediate.heapOOM;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

    public static void main(String[] args) {
        List<HeapOOM> heapOOMS = new ArrayList<>();

        while(true){
            heapOOMS.add(new HeapOOM());
        }
    }
}
