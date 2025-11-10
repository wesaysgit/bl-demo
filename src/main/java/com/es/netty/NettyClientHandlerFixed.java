package com.es.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 修复后的Netty客户端处理器
 */
@Slf4j
public class NettyClientHandlerFixed extends ChannelInboundHandlerAdapter {

    private final NettyClientFixed nettyClient;

    public NettyClientHandlerFixed(NettyClientFixed nettyClient) {
        this.nettyClient = nettyClient;
    }

    /**
     * 当客户端连接服务器完成就会触发该方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接成功: {}", ctx.channel().remoteAddress());
    }

    /**
     * 当通道有读取事件时会触发，即服务端发送数据给客户端
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String message = buf.toString(CharsetUtil.UTF_8);
        log.info("收到服务端的消息: {}", message);
        log.info("服务端的地址: {}", ctx.channel().remoteAddress());
    }

    /**
     * channel 处于不活动状态时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("连接断开，准备重连...");
        nettyClient.reconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常", cause);
        ctx.close();
    }
}



