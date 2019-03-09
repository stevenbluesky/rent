package com.rent.listener;

import com.rent.webSocket.MyWebSocketClient;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SysListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent context) {

		try {
			MyWebSocketClient.createConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
