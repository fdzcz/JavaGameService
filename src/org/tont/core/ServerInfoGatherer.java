package org.tont.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

public class ServerInfoGatherer {
	
	protected AtomicLong handleTotalNum = new AtomicLong(0);
	protected long lastHandleNum = 0L;
	protected AtomicLong handleLoginNum = new AtomicLong(0);
	private long currentSpeedPerSecond = 0L;
	private long analysePeriod = 2000L;
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	
}
