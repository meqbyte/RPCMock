package com.maeq.netty;

import com.maeq.rpc.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        RPCResponse response = (RPCResponse) o;
        System.out.println(response.toString());
    }
}
