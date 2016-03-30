package org.tont.core.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.tont.core.Configuration;
import org.tont.core.ServerInfoGatherer;
import org.tont.exceptions.ConfigParseException;


public class NettyServer implements Runnable {
	
	private Configuration config;
	protected ChannelInitializer<SocketChannel> initializer;
	private static ServerInfoGatherer gatherer;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	protected long startTime;
	
	public NettyServer(String configPath, ChannelInitializer<SocketChannel> initializer) throws ConfigParseException {
		config = new Configuration(configPath);
		System.out.println("成功从 "+configPath+" 加载配置文件");
		this.initializer = initializer;
	}
	
	public static ServerInfoGatherer Gatherer() {
		if (gatherer == null) {
			gatherer = new ServerInfoGatherer();
		}
		return gatherer;
	}
	
	public boolean checkTerminated() {
		if (bossGroup.isTerminated() && workerGroup.isTerminated()) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void bind(ChannelInitializer<SocketChannel> channelInitializer) throws InterruptedException {
		bossGroup = new NioEventLoopGroup(config.getBossThreadNum());
		workerGroup = new NioEventLoopGroup(config.getWorkerThreadNum());
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.option(ChannelOption.SO_RCVBUF, 128)
				.option(ChannelOption.SO_SNDBUF, 128)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(channelInitializer);
			
			ChannelFuture cFuture = bootstrap.bind(config.getListenPort()).sync();
			cFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void run() {
		try {
			startTime = System.currentTimeMillis();
			NettyServer.Gatherer().startDataAnalyse();
			this.bind(initializer);
		} catch (InterruptedException e) {
			
		}
	}
	
	//setter and getter
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

}
