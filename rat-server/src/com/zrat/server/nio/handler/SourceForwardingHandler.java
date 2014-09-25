package com.zrat.server.nio.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.zrat.server.controller.PortForwardingService;
import com.zrat.server.controller.PortForwardingServiceImpl;
import com.zrat.transfer.model.Route;

public class SourceForwardingHandler extends SimpleChannelUpstreamHandler{
	@Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		for(Route r:PortForwardingServiceImpl.routes){
			if (ctx.getChannel().getRemoteAddress().toString().endsWith(r.getSourcePort())){
				r.setSourceChannel(null);
			}
		}
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e){
		for(Route r:PortForwardingServiceImpl.routes){
			if (ctx.getChannel().getRemoteAddress().toString().endsWith(r.getSourcePort())){
				PortForwardingServiceImpl.routes.remove(r);
				r.setSourceChannel(ctx.getChannel());
				PortForwardingServiceImpl.routes.add(r);
			}
		}
	}
    
    @Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    	System.out.println(this.getClass().getName());
    	PortForwardingService service = new PortForwardingServiceImpl();
    	for(Route r:PortForwardingServiceImpl.routes){
			if (ctx.getChannel().getRemoteAddress().toString().endsWith(r.getSourcePort())){
				service.forward(r.getTargetIp(), Integer.valueOf(r.getTargetPort()) ,e);
			}
		}
    }
    
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }
    
}
