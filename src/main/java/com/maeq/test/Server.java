package com.maeq.test;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动，等待连接...");

        Socket socket = serverSocket.accept();
        System.out.println("客户端连接成功");

        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[4];

        while (true) {
            int len = inputStream.read(buffer);
            if (len == -1) break;

            // 直接打印接收到的字节内容
            String msg = new String(buffer, 0, len);
            System.out.println("收到数据: " + msg);
        }

        socket.close();
        serverSocket.close();
    }
}

