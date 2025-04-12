package com.maeq;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();

            System.out.println("accept.....");

//            InputStream in = socket.getInputStream();
//            ObjectInputStream ois = new ObjectInputStream(in);
//
//            Integer id = ois.readInt();
//            System.out.println("id = " + id);


            OutputStream out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(new Integer(123));
            oos.flush();


//            in.close();
            socket.close();
            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
