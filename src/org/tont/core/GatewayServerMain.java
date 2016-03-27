package org.tont.core;

import org.tont.core.netty.connector.BaseServerConnector;
import org.tont.core.netty.gateway.Gateway;
import org.tont.core.netty.send.MarketConnectionHandler;
import org.tont.exceptions.ConfigParseException;

public class GatewayServerMain {

	public static void main(String[] args) throws ConfigParseException {
		Gateway gateway = new Gateway("/GatewayConfiguration.properties");
		new Thread(gateway).start();
		BaseServerConnector marketCon = new BaseServerConnector(2185,"127.0.0.1",new MarketConnectionHandler());
		new Thread(marketCon).start();
	}
}
