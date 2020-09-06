package com.crc.io.learn.netIo.bio.threadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-03 14:18
 * @descripton: 服务端（线程池）
 */
public class Server {

    private static ExecutorService executorService = new ThreadPoolExecutor(3, 5, 3,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

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

                // 通过线程池机制去处理
                executorService.submit(new MyRunnable(socket));
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
