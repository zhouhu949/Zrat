package com.zrat.client.ui.model;

import org.jboss.netty.channel.Channel;

import com.zrat.transfer.model.BaseModel;


public class PortForwardingModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ip;
	private String targetIp;
	private int targetPort;
	private int localPort;
	private int status;
	private Channel sourceChannel;
	private Channel targetChannel;
	private Channel localChannel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public int getTargetPort() {
		return targetPort;
	}
	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}
	public int getLocalPort() {
		return localPort;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
	public Channel getLocalChannel() {
		return localChannel;
	}
	public void setLocalChannel(Channel localChannel) {
		this.localChannel = localChannel;
	}
	
}
