package com.maeq.netty;

import com.maeq.rpc.RPCRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.Method;
import java.util.HashMap;

@AllArgsConstructor
public class RPCNettyServerChannelHandler extends SimpleChannelInboundHandler<RPCRequest> {

    HashMap<String, Object> serviceMap;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest rpcRequest) throws Exception {


        String interfaceName = rpcRequest.getInterfaceName();
        Object interfaceImpl = serviceMap.get(interfaceName);

        if (interfaceImpl == null) {
            System.out.println("interfaceImpl == null");
            return;
        }

        Method method = interfaceImpl.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsType());
        Object result = method.invoke(interfaceImpl, rpcRequest.getParamsValue());
        System.out.println("result = " + result.toString());

        channelHandlerContext.writeAndFlush(result);
        channelHandlerContext.close();
    }
}
