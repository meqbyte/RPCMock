package com.maeq;

import com.maeq.entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            Random random = new Random();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeInt(random.nextInt());
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            User user = (User) ois.readObject();
            System.out.println(user.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
