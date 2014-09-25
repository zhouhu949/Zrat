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
package com.zrat.client.nio;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.FixedReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

import com.zrat.client.nio.factory.ClientNIOPipelineFactory;
import com.zrat.client.nio.factory.UDPPipelineFactory;

/**
 * Receives a list of continent/city pairs from a {@link LocalTimeClient} to
 * get the local times of the specified cities.
 *
 * @author <a href="http://www.jboss.org/netty/">The Netty Project</a>
 * @author <a href="http://gleamynode.net/">Trustin Lee</a>
 *
 * @version $Rev: 2080 $, $Date: 2010-01-26 18:04:19 +0900 (Tue, 26 Jan 2010) $
 */
public class ClientNIO {
	private static ServerBootstrap TCPBootstrap;
//	private static ConnectionlessBootstrap UDPBootstrap;
	
    public static void startListening(int port) {
        // Configure the server.
    	TCPBootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                		Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        final Timer timer = new HashedWheelTimer();
        // Set up the event pipeline factory.
        TCPBootstrap.setPipelineFactory(new ClientNIOPipelineFactory(timer));
        TCPBootstrap.setOption("tcpNoDelay", true);
        TCPBootstrap.setOption("keepAlive", true);
        TCPBootstrap.setOption("reuseAddress", true);
        TCPBootstrap.setOption("receiveBufferSize", 2048576);
//        TCPBootstrap.setOption("sendBufferSize", 1048576);
        // Bind and start to accept incoming connections.
        TCPBootstrap.bind(new InetSocketAddress(port));
        
//        UDPBootstrap = new ConnectionlessBootstrap(
//                new NioDatagramChannelFactory(
//                        Executors.newCachedThreadPool()));
//        // Set up the event pipeline factory.
//        UDPBootstrap.setPipelineFactory(new UDPPipelineFactory());
//        UDPBootstrap.setOption("broadcast", false);
//        UDPBootstrap.setOption(
//                "receiveBufferSizePredictorFactory",
//                new FixedReceiveBufferSizePredictorFactory(409600));
////        UDPBootstrap.setOption("keepAlive", true);
//        // Bind and start to accept incoming connections.
//        UDPBootstrap.bind(new InetSocketAddress(port));
        
        
    }
    
    public static void  stop(){
    	if (TCPBootstrap != null)
    	TCPBootstrap.releaseExternalResources();
//    	if (UDPBootstrap != null)
//    	UDPBootstrap.releaseExternalResources();
    }
}
