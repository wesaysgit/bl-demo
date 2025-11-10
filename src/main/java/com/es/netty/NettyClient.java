package com.es.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;

/**
 * 实现了重连的客户端
 */
public class NettyClient {

    private String host;
    private int port;
    private Bootstrap bootstrap;
    private EventLoopGroup group;

    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
        
        // 等待一段时间后关闭
        Thread.sleep(10000);
        nettyClient.shutdown();
    }

    public NettyClient() {
        // 不在构造函数中调用init()
    }

    private void init() {
        host = "127.0.0.1";
        port = 9512;
        //客户端需要一个事件循环组
        group = new NioEventLoopGroup();
        //创建客户端启动对象
        // bootstrap 可重用, 只需在NettyClient实例化的时候初始化即可.
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
                        pipeline.addLast(new NettyClientHandler(NettyClient.this));
                    }
                });
    }
    
    public void start() {
        try {
            System.out.println("开始连接服务器: " + host + ":" + port);
            init(); // 初始化配置
            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.out.println("连接服务器成功: " + future.channel().remoteAddress());
            
            // 发送测试消息
            sendMessage(future.channel());
            
            // 等待连接关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("连接服务器失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void sendMessage(Channel channel) {
        try {
            String msg = "{\"data\":{\"msg\":\"请求成功\",\"license\":\"京A00004\",\"data\":{\"message\":\"消费车牌与CPU车牌不一致\",\"data\":{\"fare\":0.01,\"psam_id\":\"\",\"terminal_no\":\"\",\"blacklist_ver\":\"2211171805\",\"tran_no\":\"\",\"category\":\"0\",\"park_time\":120,\"sys_date\":20230220,\"park_vehicle_class\":\"1\",\"dec_time\":\"\",\"pay_way\":\"B\",\"card_type\":23,\"tran_type\":\"09\",\"fare_before\":19997745.35,\"license_color\":\"0\",\"tran_category\":\"1\",\"service_code\":\"1\",\"tac\":\"\",\"lane_id\":1002002,\"obu_type\":2,\"obu_mac\":\"617E7E58\",\"obu_id\":\"3301611509044182\",\"issue_identify\":\"浙江\",\"issue_code\":\"3301\",\"card_id\":\"F00F62\",\"terminal_tran_no\":\"\",\"time\":171326,\"date\":20230220},\"code\":1009},\"tradeNo\":\"etc202302201713261584758604\",\"errmsg\":\"请求失败\",\"state\":1},\"method\":\"ystx.deduct.response\"}";
            ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes("GBK"));
            channel.writeAndFlush(buf);
            System.out.println("消息发送成功");
        } catch (UnsupportedEncodingException e) {
            System.err.println("发送消息失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void shutdown() {
        try {
            if (group != null) {
                group.shutdownGracefully();
            }
        } catch (Exception e) {
            System.err.println("关闭客户端失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Channel connect() {
        // 异步操作
        ChannelFuture connect;
        try {
            connect = bootstrap
                    .connect(host, port)
//                    .addListener(new ConnectionListener(this))// netty 启动时如果连接失败，会断线重连
                    .sync();
            Channel channel = connect.channel();
            String msg = "{\"data\":{\"msg\":\"请求成功\",\"license\":\"京A00004\",\"data\":{\"message\":\"消费车牌与CPU车牌不一致\",\"data\":{\"fare\":0.01,\"psam_id\":\"\",\"terminal_no\":\"\",\"blacklist_ver\":\"2211171805\",\"tran_no\":\"\",\"category\":\"0\",\"park_time\":120,\"sys_date\":20230220,\"park_vehicle_class\":\"1\",\"dec_time\":\"\",\"pay_way\":\"B\",\"card_type\":23,\"tran_type\":\"09\",\"fare_before\":19997745.35,\"license_color\":\"0\",\"tran_category\":\"1\",\"service_code\":\"1\",\"tac\":\"\",\"lane_id\":1002002,\"obu_type\":2,\"obu_mac\":\"617E7E58\",\"obu_id\":\"3301611509044182\",\"issue_identify\":\"浙江\",\"issue_code\":\"3301\",\"card_id\":\"F00F62\",\"terminal_tran_no\":\"\",\"time\":171326,\"date\":20230220},\"code\":1009},\"tradeNo\":\"etc202302201713261584758604\",\"errmsg\":\"请求失败\",\"state\":1},\"method\":\"ystx.deduct.response\"}";
//            ByteBuf buf = Unpooled.copiedBuffer((msg+"\r\n").getBytes("GBK"));
            ByteBuf buf = Unpooled.copiedBuffer((msg).getBytes("GBK"));
            channel.writeAndFlush(buf);
            // 关闭客户端
            connect.channel()
                    .closeFuture()
                    .sync();
            return connect.channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}