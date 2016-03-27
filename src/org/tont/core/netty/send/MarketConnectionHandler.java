package org.tont.core.netty.send;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.tont.core.netty.ServerChannelManager;
import org.tont.core.netty.gateway.Gateway;
import org.tont.proto.GameMsgEntity;

public class MarketConnectionHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ServerChannelManager.putChannel("MarketServerChannel", ctx.channel());
		System.out.println("已与市场服务器建立连接");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		GameMsgEntity msgEntity = (GameMsgEntity) msg;
		//GameMessage.PlayerAccountInfo info = GameMessage.PlayerAccountInfo.parseFrom(msgEntity.getData());
		Gateway.getSessionPool().findSession(1).getChannel().writeAndFlush(msgEntity);
	}

}
