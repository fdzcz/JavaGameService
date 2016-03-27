package org.tont.core.netty.gateway;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import org.tont.core.netty.RevMsgDecoder;
import org.tont.core.netty.SendMsgEncoder;

public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	private ChannelInboundHandlerAdapter handler;
	private static final int readerIdleTimeSeconds = 10;
	private static final int writerIdleTimeSeconds = 10;
	private static final int allIdleTimeSeconds = 0;
	
	public GatewayChannelInitializer(ChannelInboundHandlerAdapter handler) {
		this.handler = handler;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds,allIdleTimeSeconds));
		ch.pipeline().addLast("Decoder", new RevMsgDecoder());
		ch.pipeline().addLast("Encoder", new SendMsgEncoder());
		ch.pipeline().addLast(handler);
	}

}
