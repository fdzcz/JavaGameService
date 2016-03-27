package org.tont.core.global.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.tont.core.netty.global.GlobalChannelInitializer;
import org.tont.core.netty.global.GlobalServer;
import org.tont.core.netty.global.GlobalServerHandler;
import org.tont.exceptions.ConfigParseException;

public class GlobalMonitorFrame extends JFrame {

	private static final long serialVersionUID = 3063160867400741299L;
	public GlobalServer server;
	public Thread serverThread;
	private JTabbedPane tabbed;
	private JPanel gatewayPanel;

	public GlobalMonitorFrame() {
		super("Global Monitor");
		UIStyleMgr.initUIStyle();
		
		tabbed = new JTabbedPane(JTabbedPane.TOP);
		gatewayPanel = new GatewayPanel();
		tabbed.add(" 网关服务器 ", gatewayPanel);
		
		JPanel view = new JPanel();
		view.setBorder(new EmptyBorder(10, 0, 0, 0));
		view.add(tabbed);
		this.add(view);
		
		//设置窗口属性
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(960, 600);
		this.setInitPosition_Center();
		this.setResizable(false);
		this.setVisible(true);
		
		//启动监听线程
		this.startListener();
	}
	
	//设置初始坐标为屏幕正中心
	public void setInitPosition_Center() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height)
		    frameSize.height = screenSize.height;       
		if (frameSize.width > screenSize.width)
		  frameSize.width = screenSize.width;       
		this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
	}
	
	private void startListener() {
		try {
			server = new GlobalServer("/GlobalConfiguration.properties", new GlobalChannelInitializer(new GlobalServerHandler()));
			serverThread = new Thread(server);
			serverThread.start();
		} catch (ConfigParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e)
    {
		super.processWindowEvent(e);

		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			serverThread.interrupt();
			this.setVisible(false);
			while(!server.checkTerminated()) {
				
			}
			System.exit(NORMAL);
		}
    }
}
