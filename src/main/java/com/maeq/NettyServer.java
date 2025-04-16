package com.maeq;

import com.maeq.netty.RPCNettyServerChannelInitializer;
import com.maeq.service.BlogServiceImpl;
import com.maeq.service.UserServiceImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.HashMap;

public class NettyServer {
    public static void main(String[] args) throws Exception {

        HashMap<String, Object> serviceMap = new HashMap<>();
        serviceMap.put("com.maeq.service.UserService", new UserServiceImpl());
        serviceMap.put("com.maeq.service.BlogService", new BlogServiceImpl());

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new RPCNettyServerChannelInitializer(serviceMap));

            ChannelFuture future = bootstrap.bind(8888).sync();
            System.out.println("Netty server started on port 8888");
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
