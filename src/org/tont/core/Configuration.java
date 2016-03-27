package org.tont.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.tont.exceptions.ConfigParseException;

public class Configuration {
	private Properties properties;
	private String GlobalAddress;
	private int ListenPort;
	private int BossThreadNum;
	private int WorkerThreadNum;
	private int DispatchBufferSize;
	private int SendBufferSize;
	
	//DispatchBufferSize=1048576
	//SendBufferSize=1048576
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public int getDispatchBufferSize() {
		return DispatchBufferSize;
	}

	public void setDispatchBufferSize(int dispatchBufferSize) {
		DispatchBufferSize = dispatchBufferSize;
	}

	public int getSendBufferSize() {
		return SendBufferSize;
	}

	public void setSendBufferSize(int sendBufferSize) {
		SendBufferSize = sendBufferSize;
	}

	public Properties getProp() {
		return properties;
	}

	public void setProp(Properties prop) {
		this.properties = prop;
	}

	public String getGlobalAddress() {
		return GlobalAddress;
	}

	public void setGlobalAddress(String globalAddress) {
		GlobalAddress = globalAddress;
	}

	public int getListenPort() {
		return ListenPort;
	}

	public void setListenPort(int listenPort) {
		ListenPort = listenPort;
	}

	public int getBossThreadNum() {
		return BossThreadNum;
	}

	public void setBossThreadNum(int bossThreadNum) {
		BossThreadNum = bossThreadNum;
	}

	public int getWorkerThreadNum() {
		return WorkerThreadNum;
	}

	public void setWorkerThreadNum(int workerThreadNum) {
		WorkerThreadNum = workerThreadNum;
	}

	public String getGlobalIP() {
		return GlobalAddress;
	}

	public void setGlobalIP(String globalIP) {
		this.GlobalAddress = globalIP;
	}

	//Constructor
	public Configuration(String contextPath) throws ConfigParseException {
		try {
			initConfiguration(contextPath);
		} catch (IOException e) {
			throw new ConfigParseException("Error Config File !");
		}
	}
	
	public void initConfiguration(String contextPath) throws IOException {
		properties = new Properties();
		InputStream in = getClass().getResourceAsStream(contextPath);
		properties.load(in);
		GlobalAddress = properties.getProperty("GlobalAddress");
		ListenPort = Integer.parseInt(properties.getProperty("ListenPort"));
		ListenPort = ListenPort>0 && ListenPort<65535 ? ListenPort : 59427 ;
		int coreNum = Runtime.getRuntime().availableProcessors();
		BossThreadNum = Integer.parseInt(properties.getProperty("BossThreadNum"));
		BossThreadNum = BossThreadNum>0 && BossThreadNum<100 ? BossThreadNum : coreNum+1 ;
		WorkerThreadNum = Integer.parseInt(properties.getProperty("WorkerThreadNum"));
		WorkerThreadNum = WorkerThreadNum>0 && WorkerThreadNum<100 ? WorkerThreadNum : coreNum*2 ;
		DispatchBufferSize = Integer.parseInt(properties.getProperty("DispatchBufferSize"));
		SendBufferSize = Integer.parseInt(properties.getProperty("SendBufferSize"));
	}
	
	@Override
	public String toString() {
		return properties.toString();
	}
}
