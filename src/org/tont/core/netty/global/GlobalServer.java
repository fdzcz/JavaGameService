package org.tont.core.netty.global;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import org.tont.core.netty.NettyServer;
import org.tont.exceptions.ConfigParseException;

public class GlobalServer extends NettyServer {

	public GlobalServer(String configPath,
			ChannelInitializer<SocketChannel> initializer)
			throws ConfigParseException {
		super(configPath, initializer);
	}
	
	@Override
	public void run() {
		try {
			this.bind(this.initializer);
		} catch (InterruptedException e) {
		}
	}

}
