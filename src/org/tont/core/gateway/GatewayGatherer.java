package org.tont.core.gateway;

import java.util.Date;

import org.tont.core.ServerInfoGatherer;

public class GatewayGatherer extends ServerInfoGatherer {
	
	//
	public void handleLogin() {
		handleLoginNum.incrementAndGet();
	}

	@Override
	protected void Log() {
		System.out.println("************************");
		System.out.println(format.format(new Date())
			+ "  当前服务器处理请求速度 ："+getCurrentSpeedPerSecond()+" 个请求/秒\n"
			+ "一共处理"+handleLoginNum.get()+"次登录请求");
	}
	
	@Override
	protected void reportToGlobal() {
		
	}
}
