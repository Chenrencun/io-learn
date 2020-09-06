package com.crc.io.learn.fileIo.bio;

import java.io.*;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-02 15:14
 * @descripton: 文件IO（阻塞IO）
 */
public class FileBio {

    /**
     * 字节流（输出流）
     * @param url
     */
    public static void outputStream(String url){
        File file = new File(url);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("hello world!".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字节流（输入流）
     * @param url
     */
    public static void inputStream(String url){
        File file = new File(url);
        FileInputStream fileInputStream = null;
        byte[] bytes = new byte[1024];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new String(bytes, 0, bytes.length));
    }

    /**
     * 字符流（输出流）
     */
    public static void writer(String url){
        File file = new File(url);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("Hello World!");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符流（输入流）
     * @param url
     */
    public static void reader(String url){
        File file = new File(url);
        FileReader fileReader = null;
        char[] chars = new char[1024];
        try {
            fileReader = new FileReader(file);
            fileReader.read(chars);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new String(chars, 0, chars.length));
    }
}
