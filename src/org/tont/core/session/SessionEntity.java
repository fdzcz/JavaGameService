package org.tont.core.session;

import io.netty.channel.Channel;

public class SessionEntity {
	
	private int pid;
	private String token;
	private Channel channel;
	private long activeTime;
	
	public SessionEntity(int pid, String token, Channel channel) {
		this.pid = pid;
		this.token = token;
		this.channel = channel;
		activeTime = System.currentTimeMillis();
	}
	
	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Channel getChannel() {
		return channel;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public long getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(long activeTime) {
		this.activeTime = activeTime;
	}
	
}
