package com.zrat.server.nio.factory;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.compression.ZlibDecoder;
import org.jboss.netty.handler.codec.compression.ZlibEncoder;
import org.jboss.netty.handler.codec.compression.ZlibWrapper;

import com.zrat.server.nio.handler.SendScreenHandler;

public class UDPPipelineFactory implements ChannelPipelineFactory {

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("deflater", new ZlibEncoder(ZlibWrapper.ZLIB));
        pipeline.addLast("inflater", new ZlibDecoder(ZlibWrapper.ZLIB));
		pipeline.addLast("screenHandler", new SendScreenHandler());
		return pipeline;
	}
}