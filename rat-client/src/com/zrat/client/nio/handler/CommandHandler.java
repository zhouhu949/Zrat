package com.zrat.client.nio.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ChannelHandler.Sharable;

import com.zrat.client.controller.CommandService;
import com.zrat.transfer.model.TransferDataStruct;

@Sharable
public class CommandHandler extends ClientNIOHandler {

	@Override
	public void processMessage(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() instanceof TransferDataStruct) {
			TransferDataStruct data = (TransferDataStruct) e.getMessage();
			if (CommandService.COMMAND_RUNCOMAND.equals(data.getAction())) {
				server.getCommandFrame().getjTextArea()
						.append("\n");
				server.getCommandFrame().getjTextArea()
						.append(data.getObject() + "");
			} else {
				ctx.sendUpstream(e);
			}

		} else {
			ctx.sendUpstream(e);
		}
	}

}
