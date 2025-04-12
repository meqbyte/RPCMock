package com.maeq;

import com.maeq.entity.User;
import com.maeq.service.UserServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {

    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();

            System.out.println("accept.....");

            InputStream in = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);

            Integer id = ois.readInt();
            System.out.println("id = " + id);

            User user = userServiceImpl.getUserByUserId(id);

            OutputStream out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(user);
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
