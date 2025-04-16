package com.maeq;

import com.maeq.netty.NettyClientHandler;
import com.maeq.rpc.RPCRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.nio.channels.SocketChannel;

public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {

                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    ChannelPipeline pipeline = nioSocketChannel.pipeline();

                    // 和服务端保持一致的粘包处理
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast(new LengthFieldPrepender(4));

                    // 和服务端保持一致的编解码器
                    pipeline.addLast(new ObjectEncoder());
                    pipeline.addLast(new ObjectDecoder(new ClassResolver() {
                        @Override
                        public Class<?> resolve(String className) throws ClassNotFoundException {
                            return Class.forName(className);
                        }
                    }));

                    pipeline.addLast(new NettyClientHandler());
                }
            });


            ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();

            RPCRequest request = new RPCRequest("com.maeq.service.UserService", "getUserByUserId", new Object[]{1}, new Class[]{Integer.class});

            // 发送请求
            future.channel().writeAndFlush(request);

            // 等待关闭
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }


    }
}
