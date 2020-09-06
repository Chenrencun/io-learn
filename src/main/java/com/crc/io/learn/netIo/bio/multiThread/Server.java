package com.crc.io.learn.netIo.bio.multiThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-03 14:18
 * @descripton: 服务端（多线程）
 */
public class Server{

    public static void server(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("**********服务器即将启动，等待客户端的连接*************");
            while (true){
                socket = serverSocket.accept();

                // 连接建立好之后，每个连接新建一个线程去处理
                new Thread(new MyRunnable(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // 内部类（处理客户端请求）
    static class MyRunnable implements Runnable{

        private Socket socket;

        public MyRunnable(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            char[] chars = new char[1024];
            try {
                inputStream = socket.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                inputStreamReader.read(chars);
                System.out.println("客户端说：" + new String(chars, 0 ,chars.length));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStreamReader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        server();
    }

}
