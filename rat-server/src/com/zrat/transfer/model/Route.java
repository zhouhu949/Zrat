package com.zrat.transfer.model;

import org.jboss.netty.channel.Channel;

public class Route {
	private Channel sourceChannel;
	private Channel targetChannel;
	private String sourcePort;
	private String targetPort;
	private String targetIp;
	public Channel getSourceChannel() {
		return sourceChannel;
	}
	public void setSourceChannel(Channel sourceChannel) {
		this.sourceChannel = sourceChannel;
	}
	public Channel getTargetChannel() {
		return targetChannel;
	}
	public void setTargetChannel(Channel targetChannel) {
		this.targetChannel = targetChannel;
	}
	public String getSourcePort() {
		return sourcePort;
	}
	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}
	public String getTargetPort() {
		return targetPort;
	}
	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	
	
}
