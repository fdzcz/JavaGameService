package org.tont.proto;

import io.netty.channel.Channel;

public class GameMsgEntity {

	//Fields
	private short msgCode;
	private int pid;
	private String token;
	private byte [] data;
	private Channel channel;
	
	//Getters and Setters
	public short getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(short msgCode) {
		this.msgCode = msgCode;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
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
	
}
