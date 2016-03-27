package org.tont.core.netty.gateway;

import org.tont.core.ServerInfoGatherer;
import org.tont.core.disruptor.MessageDispatcher;
import org.tont.core.gateway.GatewayGatherer;
import org.tont.core.netty.NettyServer;
import org.tont.core.session.SessionPoolImp;
import org.tont.exceptions.ConfigParseException;

import com.lmax.disruptor.SleepingWaitStrategy;

public class Gateway extends NettyServer{
	
	private static SessionPoolImp sessionPool = new SessionPoolImp();
	private static GatewayGatherer gatherer = new GatewayGatherer();
	private static MessageDispatcher dispatcher = new MessageDispatcher(4096, new SleepingWaitStrategy());	//处理注册登录消息的队列
	
	public Gateway(String contextPath) throws ConfigParseException {
		super(contextPath, new GatewayChannelInitializer(new GatewayDispatchHandler(sessionPool)));
	}
	
	public static SessionPoolImp getSessionPool() {
		return sessionPool;
	}
	
	public static ServerInfoGatherer Gatherer() {
		return gatherer;
	}
	
	public static MessageDispatcher Dispatcher() {
		return dispatcher;
	}
	
}
