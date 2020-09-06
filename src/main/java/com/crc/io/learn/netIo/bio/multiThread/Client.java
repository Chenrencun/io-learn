package com.crc.io.learn.netIo.bio.multiThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author: crc
 * @version:1.0
 * @date: 2020-09-03 14:35
 * @descripton: 客户端
 */
public class Client {

    public static void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            socket = new Socket("localhost", 8888);
            outputStream = socket.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write("This is Bio！");
            outputStreamWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStreamWriter.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        client();
    }
}
