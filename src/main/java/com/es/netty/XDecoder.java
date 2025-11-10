package com.es.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class XDecoder extends ByteToMessageDecoder {

    private final Logger logger = LoggerFactory.getLogger(XDecoder.class);

    static String notDealStr = "";

    /**
     * @param ctx
     * @param in  请求的数据
     * @param out 将粘在一起的报文拆分后的结果保留起来
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        String msg = new String(bytes,"GBK");
        logger.info("收到数据msg>>>>>>"+msg+";未处理数据notDealStr>>>>>>"+ notDealStr);
        if (!isJson(msg)) {
            if (notDealStr.length() > 0) {
                msg = notDealStr + msg;
            }
        }
        if (msg.contains("{") || msg.contains("}")) {
            int count = 0;
            for (int i = 0; i < msg.length(); i++) {
                if (msg.charAt(i) == '{') {
                    count++;
                }
                if (msg.charAt(i) == '}') {
                    count--;
                }
                if (count == 0) {
                    try {
                        String dealMsg = msg.substring(0, i + 1);
                        logger.info("dealMsg>>>>>>" + dealMsg);
                        logger.info("待处理数据准备完成>>>" + dealMsg);
                        out.add(dealMsg);
                        msg = msg.substring(i + 1);
                        i = -1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
            }
        }
        notDealStr = msg;
        logger.info("数据处理完成，剩余未处理数据notDealStr>>>>>>"+ notDealStr);
    }

    public boolean isJson(String msg) {
        if (!msg.contains("{") && !msg.contains("}")) return false;
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '{') {
                count++;
            }
            if (msg.charAt(i) == '}') {
                count--;
            }
        }
        if (count == 0) flag = true;
        return flag;
    }

}
