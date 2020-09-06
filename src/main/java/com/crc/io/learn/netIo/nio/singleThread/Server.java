package com.crc.io.learn.netIo.nio.singleThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-03 15:20
 * @descripton: 服务端（单线程方式）
 */
public class Server {

    public static void server(){
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8888));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // select方法指定超时时间，非阻塞
                if (selector.select(2000) == 0) {
                    System.out.println("*");
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //如果服务端信道感兴趣的I/O操作为accept
                    if (selectionKey.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    //如果客户端信道感兴趣的I/O操作为read
                    if (selectionKey.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = socketChannel.read(buffer);
                        if (read == -1){
                            // 客户端关闭
                            selectionKey.cancel();
                        } else {
                            // 对客户端信息处理
                            System.out.println("客户端说：" + new String(buffer.array()));
                        }
                    }

                    //这里需要手动从键集中移除当前的key
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        server();
    }
}
