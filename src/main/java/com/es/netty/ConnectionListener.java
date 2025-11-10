package com.es.netty;

import io.netty.channel.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ConnectionListener implements ChannelFutureListener {
    private NettyClient nettyClient;

    public ConnectionListener(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {
            log.error("-------------客户端重新连接-----------------");
            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(() -> nettyClient.connect(), 10L, TimeUnit.SECONDS);
        }
    }



}