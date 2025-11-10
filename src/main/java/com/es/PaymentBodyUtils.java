package com.es;

import org.apache.log4j.Logger;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PaymentBodyUtils {

    private static final Logger logger = Logger.getLogger(PaymentBodyUtils.class);

    private static final int ALIPAY_BODY_MAX = 128; // 字符数
    private static final int WECHAT_BODY_MAX = 128; // 字节数

    /**
     * 截断 body 超长部分（按字符数）
     * @param body 原字符串
     */
    public static String truncateBody(String body, String clientType) {
        if (clientType == null || clientType.isEmpty()) return "";
        if (Objects.equals(clientType, ClientType.WEIXIN)) {
            return truncateWeChatBody(body);
        } else if (Objects.equals(clientType, ClientType.ALI)) {
            return truncateAlipayBody(body);
        } else {
            return "";
        }
    }

    /**
     * 截断支付宝 body 超长部分（按字符数）
     * @param body 原字符串
     * @return 截断后的字符串，不超过128字符
     */
    public static String truncateAlipayBody(String body) {
        if (body == null || body.isEmpty()) return "";
        return body.length() <= ALIPAY_BODY_MAX ? body : body.substring(0, ALIPAY_BODY_MAX);
    }

    /** 截断微信 body（字节数，UTF-8，安全不乱码） */
    public static String truncateWeChatBody(String body) {
        if (body == null || body.isEmpty()) return "";

        try {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            if (bytes.length <= WECHAT_BODY_MAX) {
                return body;
            }

            CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
            CharBuffer charBuffer = CharBuffer.wrap(body);
            ByteBuffer byteBuffer = ByteBuffer.allocate(WECHAT_BODY_MAX);

            while (charBuffer.hasRemaining()) {
                CoderResult result = encoder.encode(charBuffer, byteBuffer, true);
                if (result.isOverflow()) {
                    break; // 达到字节上限，停止编码
                } else if (result.isError()) {
                    result.throwException(); // 遇到无法编码字符
                }
            }
            byteBuffer.flip();
            return StandardCharsets.UTF_8.decode(byteBuffer).toString();
        } catch (Exception e) {
            logger.error("微信商品描述截断异常", e);
            return "";
        }
    }

}
