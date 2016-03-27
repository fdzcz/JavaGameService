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
			+ " 当前网关服务器处理请求速度"+getCurrentSpeedPerSecond()+" 个请求/每秒\n"
			+"其中登录请求"+handleLoginNum.get()+"个");
	}
	
	@Override
	protected void reportToGlobal() {
		
	}
}
