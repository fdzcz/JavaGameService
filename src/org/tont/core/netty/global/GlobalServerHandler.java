package org.tont.core.netty.global;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.tont.core.global.ui.GlobalMonitorFrame;
import org.tont.proto.GameMsgEntity;
import org.tont.proto.ServerReport;
import org.tont.util.ConstantUtil;

@Sharable
public class GlobalServerHandler extends ChannelInboundHandlerAdapter {
	
	public final String GATEWAY = "GatewayServerChannel";
	public final String MARKET = "MarketServerChannel";
	public final String BATTLE = "BattleServerChannel";
	public final String SCENE = "SceneServerChannel";
	private final String CLOSE = ConstantUtil.CLOSE;
	
	private GlobalMonitorFrame frame;
	
	public GlobalServerHandler(GlobalMonitorFrame frame) {
		this.frame = frame;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		GameMsgEntity msgEntity = (GameMsgEntity) msg;
		ServerReport.ServerReportEntity entity = ServerReport.ServerReportEntity.parseFrom(msgEntity.getData());
		
		switch (msgEntity.getMsgCode()) {
			case 86:
				frame.noticeGatewayPanel(ctx.channel().remoteAddress(), entity);
				break;
				
			case 87:
				frame.noticeMarketPanel(ctx.channel().remoteAddress(), entity);
				break;
	
			default:
				break;
		};
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		frame.removeDisconnectPanel(ctx.channel().remoteAddress());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		if (cause.getMessage().equals(CLOSE)) {
			System.out.println("服务器 " + ctx.channel().remoteAddress() + "断开了连接");
		}
	}
}
