package com.zrat.client.nio.handler;

import java.net.InetSocketAddress;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.zrat.client.ui.Client;

public class PortForwardingHandler extends SimpleChannelUpstreamHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		String id = ((InetSocketAddress) e.getChannel().getLocalAddress()).getPort()+"";
		if (Client.getPort(id).getStatus() !=1 && Client.getPort(id).getTargetChannel() ==null){
			Client.getPort(id).setStatus(1);
			Client.getPort(id).setTargetChannel(ctx.getChannel());
		}else {
			Client.getPort(id).setSourceChannel(ctx.getChannel());
		}
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		String id = ((InetSocketAddress) e.getChannel().getLocalAddress()).getPort()+"";
		if (Client.getPort(id) != null && !Client.getPort(id).getTargetChannel().isReadable()){
			Client.getPort(id).setStatus(0);
			Client.getPort(id).setSourceChannel(null);
		}
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String id = ((InetSocketAddress) e.getChannel().getLocalAddress()).getPort()+"";
		if (e.getChannel().getId().equals(Client.getPort(id).getSourceChannel().getId())){
//			e.getChannel().write(message, remoteAddress);
			Client.getPort(id).getTargetChannel().write(e.getMessage());
		}else if(e.getChannel().getId().equals(Client.getPort(id).getTargetChannel().getId())){
			Client.getPort(id).getSourceChannel().write(e.getMessage());
			//future.addListener(ChannelFutureListener.CLOSE);
		}else{
			ctx.sendUpstream(e);
		}
	}
	
}
