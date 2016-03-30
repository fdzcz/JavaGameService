package org.tont.core.netty.send;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.tont.core.netty.ServerChannelManager;

public class GlobalConnectionHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ServerChannelManager.putChannel("GlobalServerChannel", ctx.channel());
		System.out.println("成功连接至Global监控端");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//do sth
	}
	
}
