package com.crc.io.learn.fileIo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-02 17:43
 * @descripton: 文件IO（非阻塞IO）
 */
public class FileNio {

    public static void outputNioChannel(String url){
        File file = new File(url);
        FileOutputStream outputStream = null;
        FileChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello World!".getBytes());
        try {
            outputStream = new FileOutputStream(file);
            channel = outputStream.getChannel();
            buffer.flip();
            channel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void inputNioChannel(String url){
        File file = new File(url);
        FileInputStream inputStream = null;
        FileChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            inputStream = new FileInputStream(file);
            channel = inputStream.getChannel();
            channel.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new String(buffer.array()));
    }
}
