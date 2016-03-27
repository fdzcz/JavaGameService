package org.tont.core.netty.gateway;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import org.tont.core.netty.ServerChannelManager;
import org.tont.core.session.SessionEntity;
import org.tont.core.session.SessionPoolImp;
import org.tont.proto.GameMsgEntity;

public class GatewayDispatchHandler extends ChannelInboundHandlerAdapter {
	
	private final String CLOSE = "远程主机强迫关闭了一个现有的连接。";
	private final String MARKET = "MarketServerChannel";
	private SessionPoolImp sessionPool;
	
	public GatewayDispatchHandler(SessionPoolImp sessionPool) {
		super();
		this.sessionPool = sessionPool;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		SessionEntity session = new SessionEntity(1, "", ctx.channel());
		sessionPool.setSession(1, session);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		GameMsgEntity msgEntity = (GameMsgEntity) msg;
		msgEntity.setChannel(ctx.channel());
		switch (msgEntity.getMsgCode()) {
			case 100:	//注册与登录等
				Gateway.Dispatcher().onData(msgEntity);
				break;
			case 200:	//交易等
				Gateway.Gatherer().handleRequest();
				ServerChannelManager.getChannel(MARKET).writeAndFlush(msgEntity);
				break;
			default:
				break;
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		if (cause.getMessage().equals(CLOSE)) {
			System.out.println("玩家 " + ctx.channel().remoteAddress() + "断开了连接");
		}
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE || event.state() == IdleState.WRITER_IDLE || event.state() == IdleState.ALL_IDLE) {
				System.out.println("玩家 " + ctx.channel().remoteAddress() + " read/write idle");
				ctx.close();
			}
		}
	}
}
