package com.es.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * 修复后的Netty客户端
 */
@Slf4j
public class NettyClientFixed {

    private String host;
    private int port;
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private Channel channel;

    public static void main(String[] args) throws Exception {
        NettyClientFixed nettyClient = new NettyClientFixed();
        nettyClient.start();
        
        // 等待一段时间后关闭
        Thread.sleep(10000);
        nettyClient.shutdown();
    }

    public NettyClientFixed() {
        init();
    }

    private void init() {
        host = "127.0.0.1";
        port = 9512;
        //客户端需要一个事件循环组
        group = new NioEventLoopGroup();
        //创建客户端启动对象
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 处理IO事件
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 使用this引用，避免循环依赖
                        pipeline.addLast(new NettyClientHandlerFixed(NettyClientFixed.this));
                    }
                });
    }
    
    public void start() {
        try {
            log.info("开始连接服务器: {}:{}", host, port);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            this.channel = future.channel();
            log.info("连接服务器成功: {}", channel.remoteAddress());
            
            // 发送测试消息
            sendMessage();
            
        } catch (InterruptedException e) {
            log.error("连接服务器失败", e);
            Thread.currentThread().interrupt();
        }
    }
    
    public void sendMessage() {
        if (channel != null && channel.isActive()) {
            try {
                String msg = "{\"data\":{\"msg\":\"请求成功\",\"license\":\"京A00004\",\"data\":{\"message\":\"消费车牌与CPU车牌不一致\",\"data\":{\"fare\":0.01,\"psam_id\":\"\",\"terminal_no\":\"\",\"blacklist_ver\":\"2211171805\",\"tran_no\":\"\",\"category\":\"0\",\"park_time\":120,\"sys_date\":20230220,\"park_vehicle_class\":\"1\",\"dec_time\":\"\",\"pay_way\":\"B\",\"card_type\":23,\"tran_type\":\"09\",\"fare_before\":19997745.35,\"license_color\":\"0\",\"tran_category\":\"1\",\"service_code\":\"1\",\"tac\":\"\",\"lane_id\":1002002,\"obu_type\":2,\"obu_mac\":\"617E7E58\",\"obu_id\":\"3301611509044182\",\"issue_identify\":\"浙江\",\"issue_code\":\"3301\",\"card_id\":\"F00F62\",\"terminal_tran_no\":\"\",\"time\":171326,\"date\":20230220},\"code\":1009},\"tradeNo\":\"etc202302201713261584758604\",\"errmsg\":\"请求失败\",\"state\":1},\"method\":\"ystx.deduct.response\"}";
                ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes("GBK"));
                channel.writeAndFlush(buf);
                log.info("消息发送成功");
            } catch (UnsupportedEncodingException e) {
                log.error("发送消息失败", e);
            }
        }
    }
    
    public void reconnect() {
        if (group != null && !group.isShutdown()) {
            group.schedule(() -> {
                try {
                    log.info("尝试重新连接...");
                    start();
                } catch (Exception e) {
                    log.error("重连失败", e);
                }
            }, 5, TimeUnit.SECONDS);
        }
    }
    
    public void shutdown() {
        try {
            if (channel != null && channel.isActive()) {
                channel.close().sync();
            }
        } catch (InterruptedException e) {
            log.error("关闭连接失败", e);
            Thread.currentThread().interrupt();
        } finally {
            if (group != null) {
                group.shutdownGracefully();
            }
        }
    }
}



