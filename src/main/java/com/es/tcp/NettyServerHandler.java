package com.es.tcp;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelHandlerAdapter {

    private TcpHandle tcpHandle;

    public NettyServerHandler(TcpHandle tcpHandle) {
        this.tcpHandle = tcpHandle;
    }

    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    boolean flag = false;

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        logger.info("连接的客户端地址:" + ctx.channel().remoteAddress());
        logger.info("连接的客户端ID:" + ctx.channel().id());
        ctx.writeAndFlush("client" + InetAddress.getLocalHost().getHostName() + "success connected！ \n");
        //StaticVar.ctxList.add(ctx);
        //StaticVar.chc = ctx;
        super.channelActive(ctx);
    }


    //收到数据时调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String m = (String) msg + "}";//补上分隔符
            logger.info("服务端接受的消息: " + m);
            handleMsg(ctx, m);
        } finally {
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
        }
    }

    /*
        处理接收来的消息
     */
    private void handleMsg(ChannelHandlerContext ctx, Object msg) {
        ExecutorService es = Executors.newCachedThreadPool();;
        es.execute(new Runnable() {
            public void run() {
                if (msg != null && !"".equals(msg.toString())) {
                    String mesg = msg.toString();
                    Channel channel = ctx.channel();
                    String clientIp = channel.remoteAddress().toString();
                    JSONObject msgJson = JSONObject.parseObject(mesg);
                    if (msgJson != null) {
                        //处理消息
                        //获取操作类型
                        String cmd = msgJson.getString("method");
                        String boxNo = msgJson.getString("box_no");
                        String bolinkResult = "";
                        switch (cmd) {
                            case "etc.heartbeat":
                                bolinkResult = tcpHandle.heartBeat(boxNo, clientIp);
                                NettyChannelMap.add(boxNo, channel);
                                doBackMessage(bolinkResult, channel);
                                break;
                            case "obu.upload":
                                tcpHandle.cacheRsuip(msgJson);
                                break;
                            case "etc.deduct.response":
                                bolinkResult = tcpHandle.jyBoxDeductBack(msgJson);
                                doBackMessage(bolinkResult, channel);
                                break;
                        }
                    } else {
                        //消息为空
                        JSONObject result = new JSONObject();
                        result.put("code", 1);
                        result.put("msg", "数据接收失败");
                        doBackMessage(result.toString(), channel);
                    }
                }
            }
        });
    }


    /**
     * 消息返回给车场SDK
     *
     * @param mesg
     */
    private void doBackMessage(String mesg, Channel channel) {
        if (channel != null && channel.isActive()
                && channel.isWritable()) {
            try {
                byte[] req;
                req = mesg.getBytes(Charset.forName("GBK"));
                ByteBuf buf = Unpooled.buffer(req.length);
                buf.writeBytes(req);
                channel.writeAndFlush(buf);
                if (mesg != null && mesg.length() > 10) {
                    logger.info("发送到金溢云盒=>>>>>:" + mesg + ",channel:" + channel.toString());
                }
            } catch (Exception e) {
                logger.error("发送消息到金溢云盒异常...", e);
            }
        } else {
            logger.error("客户端已断开连接...");
        }
    }


}
