package com.es.tcp;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;


@Component
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private TcpHandle tcpHandle;

    public NettyServer(TcpHandle tcpHandle) {
        this.tcpHandle = tcpHandle;
    }

    public void run() throws Exception {
        //NioEventLoopGroup是用来处理IO操作的多线程事件循环器
        EventLoopGroup bossGroup = new NioEventLoopGroup();  // 用来接收进来的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();// 用来处理已经被接收的连接
        Charset charset = Charset.forName("GBK");

        try {
            ServerBootstrap server = new ServerBootstrap();//是一个启动NIO服务的辅助启动类
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)  // 这里告诉Channel如何接收新的连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 自定义处理类 向pipeline加⼊分隔符解码器
                            ByteBuf delimiter = Unpooled.copiedBuffer("}".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, true, delimiter));
                            ch.pipeline().addLast(new StringDecoder(charset));
                            ch.pipeline().addLast(new NettyServerHandler(tcpHandle));
                        }
                    });
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.childOption(ChannelOption.SO_KEEPALIVE, true);
            int port = Integer.parseInt("9999");
            ChannelFuture f = server.bind(port).sync();// 绑定端口，开始接收进来的连接
            logger.info("服务端启动成功...");
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully(); //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            workerGroup.shutdownGracefully();
        }

    }

    public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            //这里的handler是线程安全的，所以添加了Sharable注解，共用一个实例
            Charset charset = Charset.forName("GBK");
            //SensorServerHandler sensorHandler = new SensorServerHandler();
            NettyServerHandler sensorHandler = new NettyServerHandler(tcpHandle);
            channel.pipeline()
                    .addLast(new LineBasedFrameDecoder(1024 * 4))
                    .addLast(new StringDecoder(charset))
                    .addLast(sensorHandler);
        }
    }
}

