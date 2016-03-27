package org.tont.core.netty.market;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.tont.core.netty.NettyServer;
import org.tont.proto.GameMsgEntity;

public class MarketServerHandler extends ChannelInboundHandlerAdapter {
	
	private final String CLOSE = "";
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		GameMsgEntity msgEntity = (GameMsgEntity) msg;
		msgEntity.setChannel(ctx.channel());
		switch (msgEntity.getMsgCode()) {
			case 100:
				//Gateway.dispatcher.onData(msgEntity);
				break;
			case 200:
				NettyServer.Gatherer().handleRequest();
				ctx.writeAndFlush(msgEntity);
				break;
			default:
				break;
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		if (cause.getMessage().equals(CLOSE)) {
			System.out.println("网关服务器 " + ctx.channel().remoteAddress() + " 断开了连接");
		}
	}
	
}
