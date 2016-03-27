package org.tont.core.session;

import io.netty.channel.Channel;

public class SessionPoolImp implements SessionPool {
	
	private final int count = 1000000;
	private SessionEntity [] sessions = new SessionEntity[count];
	private final int timeout = 300*1000;	//∫¡√Î
	
	public SessionEntity findSession(int pid) {
		return sessions[pid];
	}
	
	public void removeSession(int pid) {
		sessions[pid] = null;
	}
	
	public void setSession(int pid, SessionEntity session) {
		if (sessions[pid] != null) {
			if (isTimeout(pid) == 1) {
				sessions[pid] = session;
			}
		} else {
			sessions[pid] = session;
		}
	}
	
	public int isTimeout(int pid) {
		if (sessions[pid] == null) {
			return -1;
		}
		return (sessions[pid].getActiveTime() + timeout) < System.currentTimeMillis() ? 1 : 0;
	}

	@Override
	public Channel getChannel(int pid) {
		return sessions[pid].getChannel();
	}
	
}
