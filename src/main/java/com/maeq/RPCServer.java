package com.maeq;

import com.maeq.entity.User;
import com.maeq.rpc.RPCRequest;
import com.maeq.rpc.RPCResponse;
import com.maeq.service.UserService;
import com.maeq.service.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {

    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("accept.....");

                InputStream in = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);

//                Integer id = ois.readInt();
//                System.out.println("id = " + id);
//                User user = userServiceImpl.getUserByUserId(id);

                RPCRequest request = (RPCRequest) ois.readObject();
                Method method = UserService.class.getMethod(request.getMethodName(), request.getParamsType());
                Object result = method.invoke(userServiceImpl, request.getParamsValue());
                System.out.println("result = " + result.toString());


                OutputStream out = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(RPCResponse.success(result));
                oos.flush();


                ois.close();
                oos.close();
                socket.close();
            }

            // unreachable ,why
//            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
