package com.maeq;

import com.maeq.entity.User;
import com.maeq.rpc.RPCRequest;
import com.maeq.rpc.RPCResponse;
import com.maeq.rpc.WorkThread;
import com.maeq.service.BlogServiceImpl;
import com.maeq.service.UserService;
import com.maeq.service.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RPCServer {

    public static void main(String[] args) {

        HashMap<String, Object> serviceMap = new HashMap<>();
        serviceMap.put("com.maeq.service.UserService", new UserServiceImpl());
        serviceMap.put("com.maeq.service.BlogService", new BlogServiceImpl());


        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("accept.....");

                new Thread(new WorkThread(socket,serviceMap)).start();

            }


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
