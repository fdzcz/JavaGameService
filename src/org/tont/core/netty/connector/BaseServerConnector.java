package org.tont.core.netty.connector;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.tont.core.netty.RevMsgDecoder;
import org.tont.core.netty.SendMsgEncoder;

public class BaseServerConnector implements Runnable {
	
	private int port;
	private String host;
	private final ChannelInboundHandlerAdapter handler;
	
	public BaseServerConnector(int port, String ip, ChannelInboundHandlerAdapter handler) {
		this.port = port;
		this.host = ip;
		this.handler = handler;
	}
	
	public void connect() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(group)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast("Decoder", new RevMsgDecoder());
						ch.pipeline().addLast("Encoder", new SendMsgEncoder());
						ch.pipeline().addLast(handler);
					}
					
				});
			
			ChannelFuture cFuture = boot.connect(host, port).sync();
			cFuture.channel().closeFuture().sync();
			
		} finally {
			group.shutdownGracefully();
		}
	}

	@Override
	public void run() {
		try {
			this.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
