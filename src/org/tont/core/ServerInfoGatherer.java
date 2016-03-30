package org.tont.core;

import io.netty.channel.Channel;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.tont.core.netty.ServerChannelManager;
import org.tont.proto.GameMsgEntity;
import org.tont.proto.ServerReport;

public class ServerInfoGatherer {
	
	private static final String GLOBAL = "GlobalServerChannel";
	
	protected AtomicLong handleTotalNum = new AtomicLong(0);
	protected long lastHandleNum = 0L;
	protected AtomicLong handleLoginNum = new AtomicLong(0);
	private long currentSpeedPerSecond = 0L;
	private long analysePeriod = 2000L;
	
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	protected Sigar sigar = new Sigar();
	
	public boolean isLog = true;
	
	private Timer timer = new Timer();
	private TimerTask dataAnalyse = new TimerTask() {
		@Override
		public void run() {
			updateHandleNum();
			reportToGlobal();
			if (isLog)
				Log();
		}
	};
	
	private void updateHandleNum() {
		long nowNum = handleTotalNum.get();
		currentSpeedPerSecond = (nowNum - lastHandleNum)/(analysePeriod/1000L);
		lastHandleNum = nowNum;
	}
	
	protected void reportToGlobal() {
		Channel globalChannel = ServerChannelManager.getChannel(GLOBAL);
		if (globalChannel != null) {
			int cpuCount = Runtime.getRuntime().availableProcessors();
			int cpuRatio = this.getAvgCpuUsingRatio();
			long freeMemory = Runtime.getRuntime().freeMemory();
			long totalMemory = Runtime.getRuntime().totalMemory();
			ServerReport.ServerReportEntity.Builder builder = ServerReport.ServerReportEntity.newBuilder();
			builder.setCpuCount(cpuCount);
			builder.setCpuRatio(cpuRatio);
			builder.setMemoryFree(freeMemory);
			builder.setMemoryTotal(totalMemory);
			ServerReport.ServerReportEntity report = builder.build();
			GameMsgEntity msg = new GameMsgEntity();
			msg.setMsgCode((short)86);
			msg.setData(report.toByteArray());
			globalChannel.writeAndFlush(msg);
		}
	}
	
	//增加处理请求的计数
	public void handleRequest() {
		handleTotalNum.incrementAndGet();
	}
	
	public long getCurrentSpeedPerSecond() {
		return currentSpeedPerSecond;
	}
	
	//启动数据收集定时器
	public void startDataAnalyse() {
		timer.schedule(dataAnalyse, 3000, analysePeriod);	//��ʱ3��
	}
	
	//输出日志
	protected void Log() {
		System.out.println("************************");
		System.out.println(format.format(new Date())
			+ " 当前服务器处理请求速度"+getCurrentSpeedPerSecond()+" 个请求/每秒");
	}
	
	//Getter and Setter
	public long getAnalysePeriod() {
		return analysePeriod;
	}

	public void setAnalysePeriod(long analysePeriod) {
		this.analysePeriod = analysePeriod;
	}
	
	//Get System Info by Sigar or Jvm
	
	public int getAvgCpuUsingRatio() {
		CpuPerc cpuList[] = null;  
		try {  
		    cpuList = sigar.getCpuPercList();  
		} catch (SigarException e) {  
		    e.printStackTrace();  
		}  
		double cpuUsedSum = 0;
		for (int i = 0; i < cpuList.length; i++) {
			double cpuUsed = cpuList[i].getCombined();
			cpuUsedSum += cpuUsed;
		}
		return (int) (cpuUsedSum / cpuList.length * 100);
	}
	
	public long getFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}
	
	public long getTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}
	
	//For test
	public static void main(String[] args) throws Exception {
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        System.out.println(ip);
        String vmVersion = System.getProperty("java.version");
        System.out.println(vmVersion);
	}
	
}
