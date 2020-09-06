package com.crc.io.learn.netIo.nio.singleThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-03 16:24
 * @descripton: 客户端
 */
public class Client {

    public static void client(){
        SocketChannel socketChannel = null;
        Selector selector = null;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8888));
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (!socketChannel.finishConnect()){
                System.out.println("正在连接中");
            }
            System.out.println("成功连接到服务端");
            // 向服务端发送信息
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("This is Nio!".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        client();
    }
}
