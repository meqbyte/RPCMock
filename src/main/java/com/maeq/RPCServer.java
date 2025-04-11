package com.maeq;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();

            InputStream in = socket.getInputStream();

            int data;
            while ((data = in.read()) != -1) {
                System.out.println("data = " + data);
            }

            in.close();
            socket.close();
            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
