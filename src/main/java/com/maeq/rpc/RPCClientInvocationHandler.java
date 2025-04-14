package com.maeq.rpc;

import com.maeq.util.IOClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RPCClientInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("here is in invocation handler....");

        RPCRequest request = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsType(method.getParameterTypes())
                .paramsValue(args)
                .build();
        RPCResponse rpcResponse = IOClient.sendRequest(request);

        return rpcResponse.getData();
    }
}
