package com.maeq.util;

import com.maeq.rpc.RPCRequest;
import com.maeq.rpc.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {

    public static RPCResponse sendRequest(RPCRequest rpcRequest) {

        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rpcRequest);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RPCResponse rpcResponse = (RPCResponse) ois.readObject();

            System.out.println("IOClient rpcResponse = " + rpcResponse.toString());

            return rpcResponse;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
