package com.zrat.server.controller;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.MessageEvent;


public interface PortForwardingService {

	public static final String COMMAND_PORTFORWARDING = "portForwarding";
	public static final String COMMAND_STOPFORWARDING = "stopForwarding";
	
	public abstract void startForward(String[] args);
	public abstract Channel forward(String ip,int port,MessageEvent e);
	public abstract void stopForward(String[] args);

}