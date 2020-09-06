package com.crc.io.learn.netIo.bio.singleThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-03 14:18
 * @descripton: 服务端（单线程）
 */
public class Server {

    public static void server(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        char[] chars = new char[1024];
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("**********服务器即将启动，等待客户端的连接*************");
            while (true){
                socket = serverSocket.accept();

                // 连接建立好之后，从socket中获取输入流
                inputStream = socket.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                inputStreamReader.read(chars);
                System.out.println("客户端说：" + new String(chars, 0 ,chars.length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        server();
    }
}
