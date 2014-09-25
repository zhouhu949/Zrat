package com.zrat.client.controller;

import org.jboss.netty.channel.Channel;

import com.zrat.transfer.model.TransferDataStruct;

public abstract class BaseService {
	
	protected Channel channel;
	protected TransferDataStruct dataStruct;
	
	public BaseService(Channel channel){
		this.channel =  channel;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public TransferDataStruct getDataStruct() {
		return dataStruct;
	}

	public void setDataStruct(TransferDataStruct dataStruct) {
		this.dataStruct = dataStruct;
	}
	
	
	
}
