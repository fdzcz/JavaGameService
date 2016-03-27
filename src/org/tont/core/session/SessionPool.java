package org.tont.core.session;

import io.netty.channel.Channel;

public interface SessionPool {
	
	public Channel getChannel(int pid);
	
}
