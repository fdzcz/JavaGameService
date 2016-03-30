package org.tont.core.netty.send;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.tont.core.netty.ServerChannelManager;
import org.tont.core.netty.gateway.Gateway;
import org.tont.proto.GameMsgEntity;

import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class MarketConnectionHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ServerChannelManager.putChannel("MarketServerChannel", ctx.channel());
		System.out.println("成功连接至市场交易服务器");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		GameMsgEntity msgEntity = (GameMsgEntity) msg;
		//GameMessage.PlayerAccountInfo info = GameMessage.PlayerAccountInfo.parseFrom(msgEntity.getData());
		Gateway.getSessionPool().findSession(1).getChannel().writeAndFlush(msgEntity);
	}

}
