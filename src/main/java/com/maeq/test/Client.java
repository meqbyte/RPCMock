package com.maeq.test;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();

        for (int i = 1; i <= 30; i++) {
            String msg = "Hello" + i;
            outputStream.write(msg.getBytes()); // 发送消息
            // outputStream.flush(); // 可选加 flush
            // Thread.sleep(100); // 加间隔可减轻粘包
        }

        socket.close();
    }
}
