package org.tont.core.netty;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;

public class ServerChannelManager {
	private static Map<String,Channel> channelMap = new HashMap<String,Channel>();
	
	public static Channel getChannel(String channelName) {
		return channelMap.get(channelName);
	}
	
	public static void putChannel(String channelName, Channel channel) {
		channelMap.put(channelName, channel);
	}

}
