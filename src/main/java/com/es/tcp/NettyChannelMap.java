package com.es.tcp;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyChannelMap {
	/**存储和客户端的连接通道，ConcurrentHashMap线程安全*/
	private static final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

	public static void add(String key, Channel socketChannel) {
		channelMap.put(key, socketChannel);
	}
	
	public static Channel get(String key) {
		return channelMap.get(key);
	}

}
