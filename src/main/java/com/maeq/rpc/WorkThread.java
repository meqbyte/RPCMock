package com.maeq.rpc;

import com.maeq.service.UserService;
import lombok.AllArgsConstructor;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

@AllArgsConstructor
public class WorkThread implements Runnable {

    private Socket socket;

    private HashMap<String, Object> serviceMap ;

    @Override
    public void run() {

        try (
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ) {

            RPCRequest request = (RPCRequest) ois.readObject();

            String interfaceName = request.getInterfaceName();
            Object interfaceImpl = serviceMap.get(interfaceName);

            if (interfaceImpl == null) {
                System.out.println("interfaceImpl == null");
                return;
            }

            Method method = interfaceImpl.getClass().getMethod(request.getMethodName(), request.getParamsType());
            Object result = method.invoke(interfaceImpl, request.getParamsValue());
            System.out.println("result = " + result.toString());

            oos.writeObject(RPCResponse.success(result));
            oos.flush();

        } catch (IOException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
