package com.es.esdemo;

import java.nio.ByteBuffer;

public class UrlFormat {

    public static void main(String[] args) {
//        boolean flag = "1".equals("1");
//        System.out.println("flag = " + flag);
        ByteBuffer buffer = ByteBuffer.allocate(10);

// 1. 写模式：写入5个字节
        buffer.put("Hello".getBytes());
        System.out.println("写入后 - position: " + buffer.position() + ", limit: " + buffer.limit());
// 输出：写入后 - position: 5, limit: 10

// 2. flip() 切换到读模式
        buffer.flip();
        System.out.println("flip后 - position: " + buffer.position() + ", limit: " + buffer.limit());
// 输出：flip后 - position: 0, limit: 5

// 3. 读取部分数据
        byte[] data = new byte[3];
        buffer.get(data);
        System.out.println("读取后 - position: " + buffer.position() + ", limit: " + buffer.limit());
// 输出：读取后 - position: 3, limit: 5

// 4. compact() 切换到写模式
        buffer.compact();
        System.out.println("compact后 - position: " + buffer.position() + ", limit: " + buffer.limit());
// 输出：compact后 - position: 2, limit: 10

// 5. 继续写入新数据
        buffer.put("World".getBytes());
        System.out.println("继续写入后 - position: " + buffer.position() + ", limit: " + buffer.limit());
// 输出：继续写入后 - position: 7, limit: 10

// 6. 切换到读模式查看完整数据
        buffer.flip();
        byte[] allData = new byte[buffer.remaining()];
        buffer.get(allData);
        System.out.println("完整数据: " + new String(allData));
// 输出：完整数据: loWorld
    }

    public static void main1(String[] args) {
        Long unionId = 200768L;
        String parkId = "12321412";
        int type = 1;
        String channelId = null;
        String url = urlFormat(unionId, parkId, type, channelId);
        System.out.println("url1 = " + url);
        int type1 = 2;
        String channelId1 = "A2";
        String url1 = urlFormat(unionId, parkId, type1, channelId1);
        System.out.println("url2 = " + url1);
    }

    /**
     * 1-预付；2-出场；3-入场；4-收款
     * 出入场有通道号
     * http://beta.bolink.club/unionapi/t?p=122598
     * https://beta.bolink.club/s?id=
     * https://beta.bolink.club/unionid/parkid/type/channelid/s
     * 替换符：&&
     * */
    public static String urlFormat(Long unionId, String parkId, int type, String channelId) {
        String o = "https://beta.bolink.club/%d/%s/%d/%s/s";
        String channel = channelId;
        if (type == 1 || type == 4) channel = "&&";
        String format = String.format(o, unionId, parkId, type, channel);
        return format.replace("&&/", "");
    }
}
