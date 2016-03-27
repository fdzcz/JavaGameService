package org.tont.core.netty;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	private ChannelInboundHandlerAdapter handler;
	
	public ServerChannelInitializer(ChannelInboundHandlerAdapter handler) {
		this.handler = handler;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("Decoder", new RevMsgDecoder());
		ch.pipeline().addLast("Encoder", new SendMsgEncoder());
		ch.pipeline().addLast(handler);
	}

}
