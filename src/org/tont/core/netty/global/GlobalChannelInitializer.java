package org.tont.core.netty.global;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import org.tont.core.netty.RevMsgDecoder;
import org.tont.core.netty.SendMsgEncoder;

public class GlobalChannelInitializer extends ChannelInitializer<SocketChannel>{
	
	private ChannelInboundHandlerAdapter handler;

	public GlobalChannelInitializer(ChannelInboundHandlerAdapter handler) {
		this.handler = handler;
	}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("Decoder", new RevMsgDecoder());
		ch.pipeline().addLast("Encoder", new SendMsgEncoder());
		ch.pipeline().addLast(handler);
	}

}
