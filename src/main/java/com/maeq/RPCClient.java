package com.maeq;

import com.maeq.entity.User;
import com.maeq.rpc.RPCClientInvocationHandler;
import com.maeq.rpc.RPCRequest;
import com.maeq.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.Random;

public class RPCClient {

    public static void main(String[] args) {
        try {
//            Socket socket = new Socket("127.0.0.1", 8888);

//            Random random = new Random();
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            oos.writeInt(random.nextInt());
//            oos.flush();

//            RPCRequest request = RPCRequest.builder()
//                    .methodName("getUserByUserId")
//                    .paramsValue(new Object[]{123321})
//                    .paramsType(new Class[]{Integer.class})
//                    .build();
//            oos.writeObject(request);
//            oos.flush();

            UserService userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                    new Class[]{UserService.class},
                    new RPCClientInvocationHandler());
            User user = userService.getUserByUserId(1);
            System.out.println("user = " + user.toString());

            Integer id = userService.insertUser(user);
            System.out.println("insert id = " + id);

//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            User user = (User) ois.readObject();
//            System.out.println(user.toString());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
