package org.tont.core.netty.send;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.tont.core.netty.ServerChannelManager;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class GlobalConnectionHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ServerChannelManager.putChannel("GlobalServerChannel", ctx.channel());
		System.out.println("成功连接至Global监控端");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//接收来自Global的命令，并对服务器做相应的操作
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		//ctx.channel().eventLoop().schedule(() -> doConnect(), 1, TimeUnit.SECONDS);
	}
	
}
