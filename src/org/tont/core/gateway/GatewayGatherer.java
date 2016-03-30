package org.tont.core.gateway;

import io.netty.channel.Channel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.tont.core.ServerInfoGatherer;
import org.tont.core.netty.ServerChannelManager;
import org.tont.proto.GameMsgEntity;
import org.tont.proto.ServerReport;

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
		Channel globalChannel = ServerChannelManager.getChannel(GLOBAL);
		if (globalChannel != null) {
			//收集服务器信息
			int cpuCount = Runtime.getRuntime().availableProcessors();
			int cpuRatio = this.getAvgCpuUsingRatio();
			long freeMemory = Runtime.getRuntime().freeMemory();
			long totalMemory = Runtime.getRuntime().totalMemory();
			String osName = System.getProperty("os.name");
			String jreVersion = System.getProperty("java.version");
			long updateTime = System.currentTimeMillis();
			String ip = null;
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			//将服务器信息封装至Protocol Buffer序列化对象
			ServerReport.ServerReportEntity.Builder builder = ServerReport.ServerReportEntity.newBuilder();
			builder.setCpuCount(cpuCount);
			builder.setCpuRatio(cpuRatio);
			builder.setMemoryFree(freeMemory);
			builder.setMemoryTotal(totalMemory);
			builder.setOsName(osName);
			builder.setIpAddr(ip);
			builder.setJavaVersion(jreVersion);
			builder.setStartTime(startTime);
			builder.setUpdateTime(updateTime);
			builder.setHandleTotalNum(handleTotalNum.get());
			builder.setHandleSpeedNow(currentSpeedPerSecond);
			ServerReport.ServerReportEntity report = builder.build();
			
			//封装成数据包并发送给Global服务器
			GameMsgEntity msg = new GameMsgEntity();
			msg.setMsgCode(GatewayGatherer.GATEWAY_CODE);
			msg.setData(report.toByteArray());
			globalChannel.writeAndFlush(msg);
		}
	}
	
}
