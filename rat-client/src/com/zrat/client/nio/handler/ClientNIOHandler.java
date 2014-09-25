/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zrat.client.nio.handler;


import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.ChannelHandler.Sharable;

import com.zrat.client.ui.Client;
import com.zrat.client.ui.model.ServerModel;

@Sharable
public abstract class ClientNIOHandler extends SimpleChannelUpstreamHandler {
	protected ServerModel server = null;
	@Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e){
//		ServerModel server = new ServerModel();
//    	server.setId(e.getChannel().getId()+"");
//    	server.setChannel(e.getChannel());
//    	server.setIp(e.getChannel().getRemoteAddress());
//    	RatUIManager.getMainPanel().addServer(server);
    }
    
	@Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e){
		Client.removeServer(e.getChannel().getId());
    }
    
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e){
    	server = Client.getServer(ctx.getChannel().getId());
    	processMessage(ctx,e);
    }
    
    public abstract void processMessage(
            ChannelHandlerContext ctx, MessageEvent e);

}
