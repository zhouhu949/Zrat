package com.zrat.client.nio.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.Timer;

public class RatIdleStateHandler extends IdleStateHandler {

	public RatIdleStateHandler(Timer timer, int readerIdleTimeSeconds,
			int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(timer, readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
	}

	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent
			e) throws Exception {
			        if (e instanceof IdleStateEvent) {
			            System.out.println(new
			SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + " idlenessevent: " + e);
			            if (((IdleStateEvent) e).getState().equals(IdleState.READER_IDLE)) {
			                //writeTime(e.getChannel());
			            }
			        }
			        super.handleUpstream(ctx, e);
			    } 
	
	
	    

}
